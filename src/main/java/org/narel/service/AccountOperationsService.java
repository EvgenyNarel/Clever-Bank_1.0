package org.narel.service;

import org.narel.dao.AccountDao;
import org.narel.dao.OperationDao;
import org.narel.entity.Account;
import org.narel.entity.enums.OperationKind;
import org.narel.exception.AccountException;
import org.narel.model.CreditDebitDto;
import org.narel.model.OperationDto;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.util.List;
import java.util.UUID;

public class AccountOperationsService {

    private AccountDao accountDao;
    private OperationDao operationDao;

    private CheckService checkService;

    public OperationDto topUp(Account account, BigDecimal amount) {
        BigDecimal amountInAccount = account.getAmount();
        BigDecimal result = amountInAccount.add(amount);
        account.setAmount(result);
        accountDao.update(account);

        OperationDto operationTopUp = new OperationDto();
        operationTopUp.setId(UUID.randomUUID());
        operationTopUp.setSender(account.getOwner());
        operationTopUp.setAmount(amount);
        operationTopUp.setOperationKind(OperationKind.REPLENISHMENT);
        operationTopUp.setOperationDate(Instant.now());

        checkService.formCheck(operationTopUp);

        return operationTopUp;
    }

    public OperationDto withdraw(Account account, BigDecimal amount) {
        BigDecimal amountInAccount = account.getAmount();
        if (amount.compareTo(amountInAccount) > 0) {
            throw new AccountException("Insufficient funds");
        }
        BigDecimal result = amountInAccount.subtract(amount);
        account.setAmount(result);
        accountDao.update(account);

        OperationDto operationWithdraw = new OperationDto();
        operationWithdraw.setId(UUID.randomUUID());
        operationWithdraw.setSender(account.getOwner());
        operationWithdraw.setAmount(amount);
        operationWithdraw.setOperationKind(OperationKind.WITHDRAWAL);
        operationWithdraw.setOperationDate(Instant.now());
        checkService.formCheck(operationWithdraw);
        return operationWithdraw;
    }

    public OperationDto transfer(Account recipient, Account sender, BigDecimal amountTransfer) {
        BigDecimal amountInAccountRecipient = recipient.getAmount();
        BigDecimal amountInAccountSender = sender.getAmount();

        if (amountTransfer.compareTo(amountInAccountSender) > 0) {
            throw new AccountException("Insufficient funds");
        }
        BigDecimal resultSender = amountInAccountSender.subtract(amountTransfer);
        sender.setAmount(resultSender);
        accountDao.update(sender);
        BigDecimal resultRecipient = amountInAccountRecipient.add(amountTransfer);
        recipient.setAmount(resultRecipient);
        accountDao.update(recipient);

        OperationDto operationTransfer = new OperationDto();
        operationTransfer.setId(UUID.randomUUID());
        operationTransfer.setSender(sender.getOwner());
        operationTransfer.setRecipient(recipient.getOwner());
        operationTransfer.setAmount(amountTransfer);
        operationTransfer.setOperationKind(OperationKind.TRANSFER);
        operationTransfer.setOperationDate(Instant.now());
        checkService.formCheck(operationTransfer);

        return operationTransfer;

    }

    public CreditDebitDto makeExtract(Account recipient, Period period) {
        CreditDebitDto extractUser = new CreditDebitDto();
        extractUser.setFullName(recipient.getOwner().getFullName());
        extractUser.setAccount(recipient);
        extractUser.setCurrency(recipient.getCurrency());
        extractUser.setOpeningDate(recipient.getOpeningDate());
        extractUser.setPeriod(period);
        extractUser.getOpeningDate();
        extractUser.setAccountBalance(recipient.getAmount());

        List<OperationDto> userOperations = operationDao.getByCustomerId(recipient.getId());
        extractUser.setUserOperations(userOperations);

        checkService.formExtract(extractUser);
        return extractUser;
    }

    public List<OperationDto> getOperations(Account user, Period period) {
        CreditDebitDto creditDebitUser = new CreditDebitDto();
        creditDebitUser.setFullName(user.getOwner().getFullName());
        creditDebitUser.setAccount(user);
        creditDebitUser.setCurrency(user.getCurrency());
        creditDebitUser.setOpeningDate(user.getOpeningDate());
        creditDebitUser.setPeriod(period);
        creditDebitUser.getOpeningDate();
        creditDebitUser.setAccountBalance(user.getAmount());

        List<OperationDto> userOperations = operationDao.getByCustomerId(user.getId());
        creditDebitUser.setUserOperations(userOperations);

        checkService.formCreditDebit(creditDebitUser);
        return userOperations;
    }
}
