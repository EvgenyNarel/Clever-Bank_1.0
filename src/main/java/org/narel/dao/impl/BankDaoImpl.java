package org.narel.dao.impl;

import org.narel.dao.EntityDao;
import org.narel.entity.Bank;

import java.sql.*;
import java.util.UUID;

public class BankDaoImpl extends BaseDao<Bank> implements EntityDao<Bank> {
    private static final String GET_BANK_BY_ID_QUERY = "SELECT id, bankName FROM bank WHERE id = ?::uuid";
    private static final String DELETE_BANK = "DELETE FROM bank WHERE id = ?::uuid";

    @Override
    public Bank findById(UUID id) {

        return findById(GET_BANK_BY_ID_QUERY, id, Bank.class);
    }

    @Override
    public Bank create(Bank entity) {
        String ADD_BUNK = "INSERT INTO bank(id,bankname) VALUES (?::uuid, ?)";
        try (Connection connection = poll.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_BUNK, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, String.valueOf(entity.getId()));
            preparedStatement.setString(2, entity.getBankname());
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
    public Bank update(Bank entity) {
        String sql = "UPDATE bank SET bankname = ? WHERE id = ?::uuid";
        try (Connection connection = poll.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getBankname());
            preparedStatement.setString(2, String.valueOf(entity.getId()));
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException();
            }
            return entity;
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void delete(UUID id) {
        delete(DELETE_BANK, id);
    }

}



