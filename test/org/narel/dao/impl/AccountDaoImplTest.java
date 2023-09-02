package org.narel.dao.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.narel.connection.Pool;
import org.narel.connection.PoolImpl;
import org.narel.entity.Account;
import org.narel.entity.Bank;
import org.narel.entity.Customer;
import org.narel.entity.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;


class AccountDaoImplTest {

    public static Pool pool;
    public static AccountDaoImpl accountDao;

    @BeforeAll
    public static void init() {
        pool = PoolImpl.newPool();
        accountDao = new AccountDaoImpl(pool);
    }

    @AfterAll
    public static void close() throws Exception {
        pool.close();
    }

    @Test
    void findByIdTest() {
        Account account = accountDao.findById(UUID.fromString("63f56e33-96e9-4fa5-a445-ce90303a51c1"));
        Assertions.assertEquals(UUID.fromString("63f56e33-96e9-4fa5-a445-ce90303a51c1"), account.getId());
        Assertions.assertEquals("01ABCDEF12345678", account.getAccountNumber());
        Assertions.assertEquals(Currency.BYN, account.getCurrency());
        Assertions.assertEquals(new BigDecimal(50000), account.getAmount());
//        Assertions.assertEquals(LocalDate.of(2022, 11, 1).atStartOfDay(ZoneId.systemDefault()).toInstant(), account.getOpeningDate());
    }

    @Test
    void createTest() {
        UUID ownerId = UUID.fromString("c58c7e62-3501-41e0-9461-4af251e3804d");

        Customer owner = new Customer();
        owner.setId(ownerId);

        Bank bank = new Bank();
        bank.setId(UUID.fromString("84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a"));

        Account expected = new Account();
        expected.setId(UUID.randomUUID());
        expected.setAccountNumber("1111111");
        expected.setOwner(owner);
        expected.setBank(bank);
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
    }

    @Test
    void deleteTest() {
    }

    @Test
    void getAccountsByOwnerIdTest() {
    }
}