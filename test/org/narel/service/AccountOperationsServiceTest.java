package org.narel.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.narel.connection.Pool;
import org.narel.connection.PoolImpl;
import org.narel.dao.impl.AccountDaoImpl;
import org.narel.dao.impl.OperationDaoImpl;
import org.narel.entity.Account;
import org.narel.entity.Bank;
import org.narel.entity.Customer;
import org.narel.entity.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

class AccountOperationsServiceTest {

    public static Pool pool;
    public static AccountDaoImpl accountDao;
    public static OperationDaoImpl operationDao;


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
    void topUp() {
       AccountOperationsService testAccountOperationsService = new AccountOperationsService();
        Account account = new Account();
        account.setAmount(BigDecimal.valueOf(1000));

        testAccountOperationsService.topUp(account,new BigDecimal(3000));
        BigDecimal amount1 = account.getAmount();
        Assertions.assertEquals(account.getAmount(),amount1);


    }

    @Test
    void withdraw() {
    }

    @Test
    void transfer() {
    }

    @Test
    void makeExtract() {
    }

    @Test
    void getOperations() {
    }
}