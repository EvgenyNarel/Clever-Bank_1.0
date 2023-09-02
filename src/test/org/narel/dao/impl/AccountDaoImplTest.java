package org.narel.dao.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.narel.dao.AccountDao;
import org.narel.entity.Account;
import org.narel.entity.MoneyStatement;
import org.narel.entity.enums.Currency;
import org.narel.factory.ObjectFactory;
import org.narel.model.Period;
import org.narel.pool.Pool;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountDaoImplTest {

    //todo: asserts of Date

    public static AccountDao accountDao = ObjectFactory.getObject(AccountDao.class);

    @AfterAll
    public static void close() throws Exception {
        ObjectFactory.getObject(Pool.class).close();
    }

    @Test
    void findByIdTest() {
        Account account = accountDao.findById(UUID.fromString("13a63cf6-fea0-4995-a873-2a00cdb8c541"));
//        Assertions.assertEquals(UUID.fromString("63f56e33-96e9-4fa5-a445-ce90303a51c1"), account.getId());
//        Assertions.assertEquals("01ABCDEF12345678", account.getAccountNumber());
//        Assertions.assertEquals(Currency.BYN, account.getCurrency());
//        Assertions.assertEquals(new BigDecimal(50000), account.getAmount());
    }

    @Test
    void createTest() {
        Account expected = new Account();
        expected.setId(UUID.randomUUID());
        expected.setAccountNumber("2222222");
        expected.setOwnerId(UUID.fromString("c58c7e62-3501-41e0-9461-4af251e3804d"));
        expected.setBankId(UUID.fromString("84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a"));
        expected.setCurrency(Currency.BYN);
        expected.setAmount(new BigDecimal(10000));
        expected.setOpeningDate(LocalDate.of(2022, 11, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        accountDao.create(expected);
        Account actual = accountDao.findById(expected.getId());

        Assertions.assertEquals(expected.getAccountNumber(), actual.getAccountNumber());
        Assertions.assertEquals(expected.getAmount(), actual.getAmount());
        Assertions.assertEquals(expected.getCurrency(), actual.getCurrency());
        Assertions.assertEquals(expected.getOpeningDate(), actual.getOpeningDate());
    }

    @Test
    void updateTest() {
        Account expected = new Account();
        expected.setId(UUID.fromString("74876ad1-c643-4362-bb58-b62248a9a139"));
        expected.setAccountNumber("33333333333");
        expected.setOwnerId(UUID.fromString("fa1dfe9b-fb59-4490-813d-69abb4201371"));
        expected.setBankId(UUID.fromString("0e549729-183f-4c8e-b68c-401855423301"));
        expected.setCurrency(Currency.BYN);
        expected.setAmount(new BigDecimal(300000));
        expected.setOpeningDate(LocalDate.of(2021, 9, 2).atStartOfDay(ZoneId.systemDefault()).toInstant());

        accountDao.update(expected);
        Account actual = accountDao.findById(expected.getId());

        Assertions.assertEquals(expected.getAccountNumber(), actual.getAccountNumber());
        Assertions.assertEquals(expected.getAmount(), actual.getAmount());
        Assertions.assertEquals(expected.getCurrency(), actual.getCurrency());
        Assertions.assertEquals(expected.getOpeningDate(), actual.getOpeningDate());
    }

    @Test
    void deleteTest() {
        accountDao.delete(UUID.fromString("74876ad1-c643-4362-bb58-b62248a9a139"));
        Account account = accountDao.findById(UUID.fromString("74876ad1-c643-4362-bb58-b62248a9a139"));
        Assertions.assertNull(account);
    }

    @Test
    void getAccountsByOwnerIdTest() {
        List<Account> actual = accountDao.getAccountsByOwnerId(UUID.fromString("c58c7e62-3501-41e0-9461-4af251e3804d"));
        Assertions.assertEquals(7, actual.size());
    }

    @Test
    void getMoneyStatementTest() {
        MoneyStatement moneyStatement = accountDao.getMoneyStatement(
                UUID.fromString("c48ed62a-06eb-4c2e-b332-62e1b1676a44"),
                new Period(
                        LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant(),
                        LocalDate.of(2023, 6, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()
                )
        );

        System.out.println("efefef");

        // todo assers
    }
}