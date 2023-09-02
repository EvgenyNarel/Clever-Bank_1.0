package org.narel.dao.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.narel.dao.BankDao;
import org.narel.entity.Bank;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankDaoImpl extends BaseCrudDao<Bank> implements BankDao {
    private static class BankDaoImplHandler {
        private final static BankDaoImpl instance = new BankDaoImpl();
    }

    public static BankDaoImpl getInstance() {
        return BankDaoImpl.BankDaoImplHandler.instance;
    }
    @Override
    protected Class<Bank> clazz() {
        return Bank.class;
    }
}
