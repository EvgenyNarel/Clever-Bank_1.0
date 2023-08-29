package org.narel.dao.impl;

import org.narel.connection.Pool;
import org.narel.connection.PoolImpl;
import org.narel.dao.EntityDao;
import org.narel.mapper.ResultSetMapper;

import java.sql.*;
import java.util.UUID;

public abstract class BaseDao<T> implements EntityDao<T> {

    protected static final Pool poll = PoolImpl.newPool();



    public T findById(String query, UUID id, Class<T> clazz) {
        try (Connection connection = poll.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(id));
            return ResultSetMapper.map(preparedStatement.executeQuery(), clazz);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void delete(String query, UUID id) {

        try (Connection connection = poll.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(id));
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }


    }
}



