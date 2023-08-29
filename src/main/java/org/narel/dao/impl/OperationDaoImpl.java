package org.narel.dao.impl;

import org.narel.dao.OperationDao;
import org.narel.entity.Customer;
import org.narel.entity.Operation;

import java.sql.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.narel.entity.enums.OperationKind.TRANSFER;

public class OperationDaoImpl extends BaseDao<Operation> implements OperationDao {
    public static void main(String[] args) {
        Customer sender = new Customer();
        sender.setId(UUID.fromString("c58c7e62-3501-41e0-9461-4af251e3804d"));

        Customer recipient = new Customer();
        recipient.setId(UUID.fromString("f7500bc3-955a-44c2-868b-13576d8eb874"));

        new OperationDaoImpl().create(Operation.builder()
                        .id(UUID.fromString("dt1dfe9b-fb59-4490-813d-69abb4201371"))
                                .sender(sender)
                                        .recipient(recipient)
                                                .operationKind(TRANSFER)
                                                        .amount()
                build());

        new OperationDaoImpl().create(new Operation(UUID.fromString("dt1dfe9b-fb59-4490-813d-69abb4201371"),
                 UUID.fromString("c58c7e62-3501-41e0-9461-4af251e3804d"),
                 UUID.fromString("f7500bc3-955a-44c2-868b-13576d8eb874"),
                 TRANSFER,100000, Instant.EPOCH));
    }

    private static final String GET_OPERATION_BY_ID_QUERY = "SELECT id, senderid, recipientid, kind, amount, operationdate FROM operation WHERE id = ?::uuid";
    private static final String DELETE_OPERATION = "DELETE FROM operation WHERE id = ?::uuid";

    @Override
    public Operation findById(UUID id) {

        return findById(GET_OPERATION_BY_ID_QUERY,id,Operation.class);
    }

    @Override
    public Operation create(Operation entity) {

        String ADD_OPERATION = "INSERT INTO operation(id, senderid, recipientid, kind, amount, operationdate) VALUES (?::uuid, ?::uuid, ?::uuid, ?, ?, ?)";
        try (Connection connection = poll.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_OPERATION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, String.valueOf(entity.getId()));
            preparedStatement.setString(2, String.valueOf(entity.getSender().getId()));
            preparedStatement.setString(3, String.valueOf(entity.getRecipient().getId()));
            preparedStatement.setString(4, entity.getOperationKind().getKind());
            preparedStatement.setBigDecimal(5, entity.getAmount());
            preparedStatement.setTimestamp(7, new java.sql.Timestamp(entity.getOperationDate().toEpochMilli()));
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
    public Operation update(Operation entity) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        delete(DELETE_OPERATION,id);

    }

    @Override
    public List<Operation> getByCustomerId(UUID customerId) {
        return null;
    }
}
