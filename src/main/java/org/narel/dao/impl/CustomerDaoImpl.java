package org.narel.dao.impl;

import org.narel.entity.Customer;

import java.sql.*;
import java.util.UUID;

public class CustomerDaoImpl extends BaseDao<Customer> {
    public static void main(String[] args) {

    }

    private static final String GET_CUSTOMER_BY_ID_QUERY = "SELECT id, fullname FROM customer WHERE id = ?::uuid";
    private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE id = ?::uuid";

    @Override
    public Customer findById(UUID id) {
        return findById(GET_CUSTOMER_BY_ID_QUERY, id, Customer.class);
    }

    @Override
    public Customer create(Customer entity) {

        String ADD_CUSTOMER = "INSERT INTO customer (id,fullname) VALUES (?::uuid, ?)";
        try (Connection connection = poll.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CUSTOMER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, String.valueOf(entity.getId()));
            preparedStatement.setString(2, entity.getFullName());
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
    public Customer update(Customer entity) {

        String sql = "UPDATE customer SET fullname = ? WHERE id = ?::uuid";
        try (Connection connection = poll.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getFullName());
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
        delete(DELETE_CUSTOMER, id);
    }
}



