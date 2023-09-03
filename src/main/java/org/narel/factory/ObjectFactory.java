package org.narel.factory;

import org.narel.dao.AccountDao;
import org.narel.dao.BankDao;
import org.narel.dao.CustomerDao;
import org.narel.dao.OperationDao;
import org.narel.dao.impl.AccountDaoImpl;
import org.narel.dao.impl.BankDaoImpl;
import org.narel.dao.impl.CustomerDaoImpl;
import org.narel.dao.impl.OperationDaoImpl;
import org.narel.generator.NumberGenerator;
import org.narel.generator.impl.NumberGeneratorIml;
import org.narel.pool.Pool;
import org.narel.pool.impl.PoolImpl;
import org.narel.saver.CheckSaver;
import org.narel.saver.impl.CheckSaverImpl;
import org.narel.service.AccountService;
import org.narel.service.impl.AccountServiceImpl;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class ObjectFactory {

    private static final Map<Class<?>, Supplier<?>> clazzToObjectSupplier;

    static {
        clazzToObjectSupplier = Map.of(
                AccountDao.class, AccountDaoImpl::getInstance,
                BankDao.class, BankDaoImpl::getInstance,
                CustomerDao.class, CustomerDaoImpl::getInstance,
                OperationDao.class, OperationDaoImpl::getInstance,
                Pool.class, PoolImpl::getInstance,
                CheckSaver.class, CheckSaverImpl::getInstance,
                AccountService.class, AccountServiceImpl::getInstance,
                NumberGenerator.class, NumberGeneratorIml::getInstance

        );
    }

    public static <T> T getObject(Class<T> clazz) {
        return clazz.cast(
                Objects.requireNonNull(clazzToObjectSupplier.get(clazz), () -> {
                    throw new IllegalArgumentException("Not found the object by " + clazz);
                }).get()
        );
    }
}
