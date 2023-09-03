package org.narel.service.impl;

import org.narel.interceptor.annotations.Transactional;
import org.narel.dao.AccountDao;
import org.narel.dao.BankDao;
import org.narel.dao.CustomerDao;
import org.narel.dao.OperationDao;
import org.narel.entity.Account;
import org.narel.entity.Operation;
import org.narel.entity.enums.OperationKind;
import org.narel.exception.AccountException;
import org.narel.exception.EntityNotFoundException;
import org.narel.factory.ObjectFactory;
import org.narel.model.Period;
import org.narel.model.dto.ExtractDto;
import org.narel.model.dto.MoneyStatementDto;
import org.narel.model.dto.OperationDto;
import org.narel.saver.CheckSaver;
import org.narel.service.AccountService;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {
    private static class AccountServiceImplHandler {
        private final static AccountServiceImpl instance = new AccountServiceImpl();
    }

    public static AccountServiceImpl getInstance() {
        return AccountServiceImpl.AccountServiceImplHandler.instance;
    }

    private final AccountDao accountDao = ObjectFactory.getObject(AccountDao.class);
    private final CustomerDao customerDao = ObjectFactory.getObject(CustomerDao.class);
    private final BankDao bankDao = ObjectFactory.getObject(BankDao.class);
    private final OperationDao operationDao = ObjectFactory.getObject(OperationDao.class);
    private final CheckSaver checkSaver = ObjectFactory.getObject(CheckSaver.class);

    @Override
    @Transactional
    public OperationDto topUp(String accountNumber, BigDecimal amount) {
        Account account = getAccountOrThrow(accountNumber);
        replenishmentOperation(amount, account);

        Operation operation = Operation.builder()
                .id(UUID.randomUUID())
                .senderId(null)
                .recipientId(account.getId())
                .kind(OperationKind.REPLENISHMENT)
                .amount(amount)
                .operationDate(Instant.now())
                .build();

        accountDao.update(account);
        operationDao.create(operation);

        return saveCheckAndGet(operation);
    }

    @Transactional
    @Override
    public OperationDto withdraw(String accountNumber, BigDecimal amount) {
        Account account = getAccountOrThrow(accountNumber);
        withdrawalOperation(amount, account);

        Operation operation = Operation.builder()
                .id(UUID.randomUUID())
                .senderId(account.getId())
                .recipientId(null)
                .kind(OperationKind.WITHDRAWAL)
                .amount(amount)
                .operationDate(Instant.now())
                .build();

        accountDao.update(account);
        operationDao.create(operation);

        return saveCheckAndGet(operation);
    }

    @Transactional
    @Override
    public OperationDto transfer(String recipientAccountNumber, String senderAccountNumber, BigDecimal amount) {
        Account recipientAccount = getAccountOrThrow(recipientAccountNumber);
        Account senderAccount = getAccountOrThrow(senderAccountNumber);

        BigDecimal recipientAccountAmount = recipientAccount.getAmount();
        BigDecimal senderAccountAmount = getAmountForSubtraction(amount, senderAccount);

        withdrawalOperation(senderAccountAmount, senderAccount);
        replenishmentOperation(recipientAccountAmount, recipientAccount);

        Operation operation = Operation.builder()
                .id(UUID.randomUUID())
                .senderId(senderAccount.getId())
                .recipientId(recipientAccount.getId())
                .kind(OperationKind.TRANSFER)
                .amount(amount)
                .operationDate(Instant.now())
                .build();

        accountDao.update(senderAccount);
        accountDao.update(recipientAccount);

        return saveCheckAndGet(operation);
    }

    @Override
    public ExtractDto makeExtract(String accountNumber, Period period) {
        Account account = accountDao.getAccountByAccountNumber(accountNumber);
        ExtractDto extract = ExtractDto.builder()
                .bankName(bankDao.findById(account.getBankId()).getBankName())
                .ownerFullName(customerDao.findById(account.getOwnerId()).getFullName())
                .accountNumber(accountNumber)
                .currency(account.getCurrency())
                .openingDate(account.getOpeningDate())
                .period(period)
                .extractDate(Instant.now())
                .balance(account.getAmount())
                .operations(OperationDto.from(operationDao.getByAccountId(account.getId())))
                .build();

        checkSaver.save(extract);

        return extract;
    }

    @Override
    public MoneyStatementDto makeMoneyStatement(String accountNumber, Period period) {
        MoneyStatementDto moneyStatement = MoneyStatementDto.from(accountDao.getMoneyStatement(
                accountDao.getAccountByAccountNumber(accountNumber).getId(),period
        ));

        checkSaver.save(moneyStatement,period);

        return moneyStatement;
    }

    private OperationDto saveCheckAndGet(Operation operation) {
        OperationDto operationDto = OperationDto.from(operation);
        checkSaver.save(operationDto);
        return operationDto;
    }

    private static void replenishmentOperation(BigDecimal amount, Account account) {
        BigDecimal resultedAmount = account.getAmount().add(amount);
        account.setAmount(resultedAmount);
    }

    private static void withdrawalOperation(BigDecimal amount, Account account) {
        BigDecimal resultedAmount = getAmountForSubtraction(amount, account).subtract(amount);
        account.setAmount(resultedAmount);
    }

    private static BigDecimal getAmountForSubtraction(BigDecimal amount, Account account) {
        BigDecimal accountAmount = account.getAmount();

        if (amount.compareTo(accountAmount) > 0) {
            throw AccountException.INSUFFICIENT_FUNDS;
        }

        return accountAmount;
    }

    private Account getAccountOrThrow(String accountNumber) {
        return Objects.requireNonNull(
                accountDao.getAccountByAccountNumber(accountNumber),
                () -> {
                    throw new EntityNotFoundException(Account.class);
                }
        );
    }
}