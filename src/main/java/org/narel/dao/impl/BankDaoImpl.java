package org.narel.dao.impl;

import org.narel.connection.Pool;
import org.narel.dao.BaseDao;
import org.narel.entity.Bank;
import org.narel.exception.DAOException;

import java.sql.*;
import java.util.UUID;

public class BankDaoImpl extends BaseDao<Bank> {

    private static final String ADD_BUNK_QUERY = "INSERT INTO bank(id, bankname) VALUES (?::uuid, ?)";
    private static final String GET_BANK_BY_ID_QUERY = "SELECT id, bankName FROM bank WHERE id = ?::uuid";
    private static final String DELETE_BANK_QUERY = "DELETE FROM bank WHERE id = ?::uuid";
    private static final String UPDATE_BANK_QUERY = "UPDATE bank SET bankname = ? WHERE id = ?::uuid";

    public BankDaoImpl(Pool poll) {
        super(poll);
    }

    @Override
    public Bank findById(UUID id) {
        return findById(GET_BANK_BY_ID_QUERY, id, Bank.class);
    }

    @Override
    public Bank create(Bank entity) {
        try (Connection connection = poll.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_BUNK_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, String.valueOf(entity.getId()));
            statement.setString(2, entity.getBankName());
            if (statement.executeUpdate() == 0) {
                throw new DAOException("Creating the bank is failed, no rows is affected");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getObject(1, UUID.class));
            } else {
                throw new DAOException("The bank key isn't fetched from database");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return entity;
    }

    @Override
    public Bank update(Bank entity) {
        try (Connection connection = poll.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_BANK_QUERY)) {
            statement.setString(1, entity.getBankName());
            statement.setString(2, String.valueOf(entity.getId()));
            if (statement.executeUpdate() == 0) {
                throw new DAOException("Updating the bank is failed, no rows is affected");
            }
            return entity;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        delete(DELETE_BANK_QUERY, id);
    }
}



