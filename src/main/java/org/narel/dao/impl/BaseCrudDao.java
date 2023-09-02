package org.narel.dao.impl;


import org.narel.factory.ObjectFactory;
import org.narel.pool.Pool;
import org.narel.dao.EntityDao;
import org.narel.entity.Entity;
import org.narel.exception.DAOException;
import org.narel.utils.ResultSetMapper;
import org.narel.utils.StatementProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 *
 * @param <T>
 */
public abstract class BaseCrudDao<T extends Entity> implements EntityDao<T> {

    protected final Pool pool = ObjectFactory.getObject(Pool.class);
    protected abstract Class<T> clazz();

    @Override
    public T findById(UUID id) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = StatementProvider.statementForFindingById(connection, id, clazz())) {

            return ResultSetMapper.mapObject(statement.executeQuery(), clazz());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public T create(T entity) {
        try (Connection connection = pool.getConnection();             PreparedStatement statement = StatementProvider.statementForCreation(connection, entity)) {

            if (statement.executeUpdate() == 0) {
                throw new DAOException("Creating the " + entity.getClass().getSimpleName() + " is failed, no rows is affected");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getObject(1, UUID.class));
            } else {
                throw new DAOException("The " + entity.getClass().getSimpleName() + " key isn't fetched from database");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return entity;
    }

    @Override
    public T update(T entity) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = StatementProvider.statementForUpdating(connection, entity)) {

            if (statement.executeUpdate() == 0) {
                throw new DAOException("Updating the " + entity.getClass().getSimpleName() + " is failed, no rows is affected");
            }
            return entity;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = StatementProvider.statementForDeleting(connection, id, clazz())) {

            if (statement.executeUpdate() == 0) {
                throw new DAOException(clazz().getSimpleName() + " deleting is failed, no rows is affected");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
