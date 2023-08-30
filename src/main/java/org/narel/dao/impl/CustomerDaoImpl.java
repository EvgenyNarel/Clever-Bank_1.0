package org.narel.dao.impl;

import org.narel.connection.Pool;
import org.narel.dao.BaseDao;
import org.narel.entity.Customer;
import org.narel.exception.DAOException;

import java.sql.*;
import java.util.UUID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class CustomerDaoImpl extends BaseDao<Customer> {

    private static final String GET_CUSTOMER_BY_ID_QUERY = "SELECT id, fullname FROM customer WHERE id = ?::uuid";
    private static final String DELETE_CUSTOMER_QUERY = "DELETE FROM customer WHERE id = ?::uuid";
    private static final String ADD_CUSTOMER_QUERY = "INSERT INTO customer (id,fullname) VALUES (?::uuid, ?)";
    private static final String UPDATE_CUSTOMER_QUERY = "UPDATE customer SET fullname = ? WHERE id = ?::uuid";

    public CustomerDaoImpl(Pool poll) {
        super(poll);
    }

    @Override
    public Customer findById(UUID id) {
        return findById(GET_CUSTOMER_BY_ID_QUERY, id, Customer.class);
    }

    @Override
    public Customer create(Customer entity) {
        try (Connection connection = poll.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_CUSTOMER_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, String.valueOf(entity.getId()));
            statement.setString(2, entity.getFullName());
            if (statement.executeUpdate() == 0) {
                throw new DAOException("Creating the customer is failed, no rows is affected");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getObject(1, UUID.class));
            } else {
                throw new DAOException("The customer key isn't fetched from database");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return entity;
    }

    @Override
    public Customer update(Customer entity) {
        try (Connection connection = poll.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_QUERY)) {

            statement.setString(1, entity.getFullName());
            statement.setString(2, String.valueOf(entity.getId()));
            if (statement.executeUpdate() == 0) {
                throw new DAOException("Updating the customer is failed, no rows is affected");
            }
            return entity;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        delete(DELETE_CUSTOMER_QUERY, id);
    }
}



