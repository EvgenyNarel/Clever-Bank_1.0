package org.narel.dao.impl;

import org.narel.connection.Pool;
import org.narel.connection.PoolImpl;
import org.narel.dao.AccountDao;
import org.narel.dao.BaseDao;
import org.narel.entity.Account;
import org.narel.exception.DAOException;
import org.narel.mapper.ResultSetMapper;

import java.sql.*;
import java.util.List;
import java.util.UUID;

public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {

    private static final String GET_ACCOUNT_BY_ID_QUERY = "SELECT id, accountnumber,bankid,ownerid,currency,amount,openingdate FROM account WHERE id = ?::uuid";
    private static final String DELETE_ACCOUNT_QUERY = "DELETE FROM account WHERE id = ?::uuid";
    private static final String ADD_ACCOUNT_QUERY = "INSERT INTO account(id,accountnumber,bankid,ownerid,currency,amount,openingdate) VALUES (?::uuid, ?, ?::uuid, ?::uuid, ?, ?, ?)";
    private static final String UPDATE_ACCOUNT_QUERY = "UPDATE account SET id, accountnumber,bankid,ownerid,currency,amount,openingdate = ? WHERE id = ?::uuid,?,";
    private static final String GET_ACCOUNTS_BY_USER_ID = "SELECT id,accountnumber,bankid,ownerid,currency,amount,openingdate FROM account WHERE ownerid = ?::uuid";

    public AccountDaoImpl(Pool poll) {

        super(poll);
    }

    public static void main(String[] args) {
      AccountDaoImpl accountDao = new AccountDaoImpl(new PoolImpl());

      accountDao.update(new Account());
    }

    @Override
    public Account findById(UUID id) {
        return findById(GET_ACCOUNT_BY_ID_QUERY, id, Account.class);
    }

    @Override
    public Account create(Account entity) {
        try (Connection connection = poll.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_ACCOUNT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, String.valueOf(entity.getId()));
            statement.setString(2, entity.getAccountNumber());
            statement.setString(3, String.valueOf(entity.getBank().getId()));
            statement.setString(4, String.valueOf(entity.getOwner().getId()));
            statement.setString(5, entity.getCurrency().name());
            statement.setBigDecimal(6, entity.getAmount());
            statement.setTimestamp(7, new java.sql.Timestamp(entity.getOpeningDate().toEpochMilli()));
            if (statement.executeUpdate() == 0) {
                throw new DAOException("Creating the account is failed, no rows is affected");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getObject(1, UUID.class));
            } else {
                throw new DAOException("The account key isn't fetched from database");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return entity;
    }

    @Override
    public Account update(Account entity) {
        try (Connection connection = poll.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT_QUERY)) {
            statement.setString(1, String.valueOf(entity.getId()));
            statement.setString(2, entity.getAccountNumber());
            statement.setString(3, String.valueOf(entity.getBank().getId()));
            statement.setString(4, String.valueOf(entity.getOwner().getId()));
            statement.setString(5, entity.getCurrency().name());
            statement.setBigDecimal(6, entity.getAmount());
            statement.setTimestamp(7, new java.sql.Timestamp(entity.getOpeningDate().toEpochMilli()));
            if (statement.executeUpdate() == 0) {
                throw new DAOException("Updating the account is failed, no rows is affected");
            }
            return entity;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        delete(DELETE_ACCOUNT_QUERY, id);
    }

    @Override
    public List<Account> getAccountsByOwnerId(UUID ownerId) {
        try (Connection connection = poll.getConnection(); PreparedStatement statement = connection.prepareStatement(GET_ACCOUNTS_BY_USER_ID)) {
            statement.setString(1, String.valueOf(ownerId));
            return ResultSetMapper.mapObjects(statement.executeQuery(), Account.class);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}