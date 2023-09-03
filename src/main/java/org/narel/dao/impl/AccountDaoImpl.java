package org.narel.dao.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.narel.dao.AccountDao;
import org.narel.entity.Account;
import org.narel.entity.MoneyStatement;
import org.narel.exception.DAOException;
import org.narel.model.Period;
import org.narel.utils.ResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountDaoImpl extends BaseCrudDao<Account> implements AccountDao {

    private static final String GET_ACCOUNTS_BY_USER_ID_QUERY = "SELECT id,accountnumber,bankid,ownerid,currency,amount,openingdate FROM account WHERE ownerid = ?::uuid";
    private static final String GET_ACCOUNT_BY_ACCOUNT_NUMBER_QUERY = "SELECT id,accountnumber,bankid,ownerid,currency,amount,openingdate FROM account WHERE accountnumber = ?";
    private static final String GET_ALL_ACCOUNT_QUERY = "SELECT id,accountnumber,bankid,ownerid,currency,amount,openingdate FROM account";
    private static final String GET_MONEY_STATEMENT_QUERY = """
                    WITH income_table AS (
                        SELECT a.id accountId, SUM(o1.amount) income
                        FROM account a
                        JOIN operation o1 on a.id = o1.recipientid
                        WHERE a.id = ?
                        AND operationdate BETWEEN ? AND ?
                        GROUP BY a.id
                    ), expense_table AS (
                        SELECT a.id accountId, SUM(o1.amount) expense
                        FROM account a
                        JOIN operation o1 on a.id = o1.senderid
                        WHERE a.id = ?
                        AND operationdate BETWEEN ? AND ?
                        GROUP BY a.id
                    )

                    SELECT fullname, b.bankname, a.accountnumber, a.currency, a.openingdate, a.amount, income, expense
                    FROM account a
                    JOIN customer c on a.ownerid = c.id
                    JOIN bank b on a.bankid = b.id
                    JOIN income_table on income_table.accountId = a.id
                    JOIN expense_table on expense_table.accountId = a.id
                    WHERE a.id = ?;
            """;

    private static class AccountDaoImplHandler {
        private final static AccountDaoImpl instance = new AccountDaoImpl();
    }

    public static AccountDaoImpl getInstance() {
        return AccountDaoImpl.AccountDaoImplHandler.instance;
    }

    protected Class<Account> clazz() {
        return Account.class;
    }

    @Override
    public List<Account> getAccountsByOwnerId(UUID ownerId) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ACCOUNTS_BY_USER_ID_QUERY)) {

            statement.setString(1, String.valueOf(ownerId));
            return ResultSetMapper.mapObjects(statement.executeQuery(), Account.class);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public MoneyStatement getMoneyStatement(UUID id, Period period) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_MONEY_STATEMENT_QUERY)) {

            statement.setObject(1, id);
            statement.setTimestamp(2, new Timestamp(period.from().toEpochMilli()));
            statement.setTimestamp(3, new Timestamp(period.to().toEpochMilli()));
            statement.setObject(4, id);
            statement.setTimestamp(5, new Timestamp(period.from().toEpochMilli()));
            statement.setTimestamp(6, new Timestamp(period.to().toEpochMilli()));
            statement.setObject(7, id);

            return ResultSetMapper.mapObject(statement.executeQuery(), MoneyStatement.class);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ACCOUNT_BY_ACCOUNT_NUMBER_QUERY)) {

            statement.setString(1, accountNumber);
            return ResultSetMapper.mapObject(statement.executeQuery(), Account.class);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    @Override
    public List<Account> getAll() {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_ACCOUNT_QUERY)) {
            return ResultSetMapper.mapObjects(statement.executeQuery(), Account.class);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}

