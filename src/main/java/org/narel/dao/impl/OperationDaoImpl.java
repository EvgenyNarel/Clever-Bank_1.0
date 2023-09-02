package org.narel.dao.impl;

import org.narel.connection.Pool;
import org.narel.dao.BaseDao;
import org.narel.dao.OperationDao;
import org.narel.exception.DAOException;
import org.narel.mapper.ResultSetMapper;
import org.narel.model.OperationDto;

import java.sql.*;
import java.util.List;
import java.util.UUID;

public class OperationDaoImpl extends BaseDao<OperationDto> implements OperationDao {

    private static final String GET_OPERATION_BY_ID_QUERY = "SELECT id, senderid, recipientid, kind, amount, operationdate FROM operation WHERE id = ?::uuid";
    private static final String DELETE_OPERATION_QUERY = "DELETE FROM operation WHERE id = ?::uuid";
    private static final String ADD_OPERATION_QUERY = "INSERT INTO operation(id, senderid, recipientid, kind, amount, operationdate) VALUES (?::uuid, ?::uuid, ?::uuid, ?, ?, ?)";
    private static final String GET_OPERATION_BY_CUSTOMER_ID = "SELECT id,senderid,recipientid,kind,amount,operationdate FROM operation WHERE senderid = ?::uuid or recipientid = ?::uuid";

    public OperationDaoImpl(Pool poll) {
        super(poll);
    }

    @Override
    public OperationDto findById(UUID id) {
        return findById(GET_OPERATION_BY_ID_QUERY, id, OperationDto.class);
    }

    @Override
    public OperationDto create(OperationDto entity) {
        try (Connection connection = poll.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_OPERATION_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, String.valueOf(entity.getId()));
            statement.setString(2, String.valueOf(entity.getSender().getId()));
            statement.setString(3, String.valueOf(entity.getRecipient().getId()));
            statement.setString(4, entity.getOperationKind().getKind());
            statement.setBigDecimal(5, entity.getAmount());
            statement.setTimestamp(6, new java.sql.Timestamp(entity.getOperationDate().toEpochMilli()));
            if (statement.executeUpdate() == 0) {
                throw new DAOException("Creating the operation is failed, no rows is affected");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getObject(1, UUID.class));
            } else {
                throw new DAOException("The operation key isn't fetched from database");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return entity;
    }

    @Override
    public OperationDto update(OperationDto entity) {
        throw new UnsupportedOperationException("The operation update isn't a valid query");
    }

    @Override
    public void delete(UUID id) {
        delete(DELETE_OPERATION_QUERY, id);

    }

    @Override
    public List<OperationDto> getByCustomerId(UUID customerId) {
        try (Connection connection = poll.getConnection(); PreparedStatement statement = connection.prepareStatement(GET_OPERATION_BY_CUSTOMER_ID)) {
            statement.setString(1, String.valueOf(customerId));
            return ResultSetMapper.mapObjects(statement.executeQuery(), OperationDto.class);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}

