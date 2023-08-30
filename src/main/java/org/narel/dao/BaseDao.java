package org.narel.dao;

import lombok.RequiredArgsConstructor;
import org.narel.connection.Pool;
import org.narel.exception.DAOException;
import org.narel.mapper.ResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class BaseDao<T> implements EntityDao<T> {

    protected final Pool poll;

    public T findById(String query, UUID id, Class<T> clazz) {
        try (Connection connection = poll.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(id));
            return ResultSetMapper.mapObject(statement.executeQuery(), clazz);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void delete(String query, UUID id) {
        try (Connection connection = poll.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(id));
            if (statement.executeUpdate() == 0) {
                throw new DAOException("Entity deleting is failed, no rows is affected");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
