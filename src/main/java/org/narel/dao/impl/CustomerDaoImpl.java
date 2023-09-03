package org.narel.dao.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.narel.dao.CustomerDao;
import org.narel.dao.impl.BaseCrudDao;
import org.narel.entity.Customer;
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class CustomerDaoImpl extends BaseCrudDao<Customer> implements CustomerDao {

    private static class CustomerDaoImplHandler {
        private final static CustomerDaoImpl instance = new CustomerDaoImpl();
    }

    public static CustomerDaoImpl getInstance() {
        return CustomerDaoImpl.CustomerDaoImplHandler.instance;
    }

    @Override
    protected Class<Customer> clazz() {

        return Customer.class;
    }
}