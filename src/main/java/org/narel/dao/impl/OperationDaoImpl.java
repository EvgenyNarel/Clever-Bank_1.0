package org.narel.dao.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.narel.dao.OperationDao;
import org.narel.entity.Operation;
import org.narel.exception.DAOException;
import org.narel.utils.ResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class OperationDaoImpl extends BaseCrudDao<Operation> implements OperationDao {

    private static final String GET_OPERATIONS_BY_ACCOUNT_ID = "SELECT id,senderid,recipientid,kind,currency,amount,operationdate FROM operation WHERE senderid = ?::uuid or recipientid = ?::uuid";
    private static class OperationDaoImplHandler {
        private final static OperationDaoImpl instance = new OperationDaoImpl();
    }

    public static OperationDaoImpl getInstance() {
        return OperationDaoImpl.OperationDaoImplHandler.instance;
    }
    @Override
    protected Class<Operation> clazz() {
        return Operation.class;
    }

    @Override
    public List<Operation> getByAccountId(UUID customerId) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_OPERATIONS_BY_ACCOUNT_ID)) {

            statement.setString(1, String.valueOf(customerId));
            statement.setString(2, String.valueOf(customerId));
            return ResultSetMapper.mapObjects(statement.executeQuery(), Operation.class);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
