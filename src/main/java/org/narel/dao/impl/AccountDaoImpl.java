package org.narel.dao.impl;

import lombok.SneakyThrows;
import org.narel.entity.Account;
import org.narel.mapper.ResultSetMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountDaoImpl extends BaseDao<Account> {
    @SneakyThrows
    public static void main(String[] args) {

//         new AccountDaoImpl().getAllFieldByUser(UUID.fromString("f579ed90-170f-4273-af17-81853f06c208"));
         new AccountDaoImpl().findById(UUID.fromString("f579ed90-170f-4273-af17-81853f06c208"));
        System.out.println("d");
    }

    private static final String GET_ACCOUNT_BY_ID_QUERY = "SELECT id, accountnumber,bankid,ownerid,currency,amount,openningdate FROM account WHERE id = ?::uuid";
    private static final String DELETE_ACCOUNT = "DELETE FROM account WHERE id = ?::uuid";

    public List<Account> getAllFieldByUser(UUID ownerid) throws SQLException {
        String sql = "SELECT id, accountnumber,bankid,ownerid,currency,amount,openningdate FROM account WHERE ownerid = ?::uuid";
        List<Account> accounts = new ArrayList<>();
        Account account;
        try (Connection connection = poll.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(ownerid));
            account = ResultSetMapper.map(preparedStatement.executeQuery(), Account.class);
            accounts.add(account);
        }
        return accounts;
    }


    @Override
    public Account findById(UUID id) {

        return findById(GET_ACCOUNT_BY_ID_QUERY, id, Account.class);
    }

    @Override
    public Account create(Account entity) {

        String ADD_ACCOUNT = "INSERT INTO account(id,accountnumber,bankid,ownerid,currency,amount,openningdate) VALUES (?::uuid, ?, ?::uuid, ?::uuid, ?, ?, ?)";
        try (Connection connection = poll.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, String.valueOf(entity.getId()));
            preparedStatement.setString(2, entity.getAccountNumber());
            preparedStatement.setString(3, String.valueOf(entity.getBank().getId()));
            preparedStatement.setString(4, String.valueOf(entity.getOwner().getId()));
            preparedStatement.setString(5, entity.getCurrency().name());
            preparedStatement.setBigDecimal(6, entity.getAmount());
            preparedStatement.setTimestamp(7, new java.sql.Timestamp(entity.getOpeningDate().toEpochMilli()));
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException();
            }
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getObject(1, UUID.class));
            } else {
                throw new IllegalStateException();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return entity;
    }

    @Override
    public Account update(Account entity) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        delete(DELETE_ACCOUNT, id);

    }

}
