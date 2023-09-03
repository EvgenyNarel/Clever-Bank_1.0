package org.narel.generator.impl;

import org.narel.exception.DAOException;
import org.narel.factory.ObjectFactory;
import org.narel.generator.NumberGenerator;
import org.narel.pool.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NumberGeneratorIml implements NumberGenerator {

    protected final Pool pool = ObjectFactory.getObject(Pool.class);
    private static final String GET_NEXT_CHECK_NUMBER_QUERY = "SELECT nextval('check_sequence')";
    private static final String GET_NEXT_EXTRACT_NUMBER_QUERY = "SELECT nextval('extract_sequence')";
    private static final String GET_NEXT_MONEY_STATEMENT_NUMBER_QUERY = "SELECT nextval('money_statement_sequence')";

    @Override
    public long getCheckNumber() {
        return getNumber(GET_NEXT_CHECK_NUMBER_QUERY);
    }

    @Override
    public long getExtractNumber() {
        return getNumber(GET_NEXT_EXTRACT_NUMBER_QUERY);

    }

    @Override
    public long getMoneyStatementNumber() {
        return getNumber(GET_NEXT_MONEY_STATEMENT_NUMBER_QUERY);
    }

    public long getNumber(String sql) {
        try (Connection connection = pool.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("nextval");
            }

            throw new DAOException("Unable to generate sequence number");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private static class CheckNumberGeneratorImlHandler {
        private final static NumberGeneratorIml instance = new NumberGeneratorIml();
    }

    public static NumberGeneratorIml getInstance() {
        return NumberGeneratorIml.CheckNumberGeneratorImlHandler.instance;
    }
}
