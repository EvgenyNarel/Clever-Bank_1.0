package org.narel.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.narel.entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;
import java.lang.reflect.Field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StatementProvider {

    @SneakyThrows
    public static <T extends Entity> PreparedStatement statementForCreation(Connection connection, T object) {
        return connection.prepareStatement(
                "INSERT INTO " + object.getClass().getSimpleName().toLowerCase()
                        + "(" + mergeFieldNames(object.getClass()) + ")" + " VALUES " + mergeFieldValues(object),
                Statement.RETURN_GENERATED_KEYS
        );
    }

    @SneakyThrows
    public static <T extends Entity> PreparedStatement statementForUpdating(Connection connection, T object) {
        return connection.prepareStatement(
                "UPDATE " + object.getClass().getSimpleName().toLowerCase() + " SET "
                        + mergeFieldNameToValue(object) + " WHERE id='" + object.getId() + "'"
        );
    }

    @SneakyThrows
    public static <T extends Entity> PreparedStatement statementForDeleting(Connection connection, UUID id, Class<T> clazz) {
        return connection.prepareStatement(
                "DELETE FROM " + clazz.getSimpleName().toLowerCase() + "  WHERE id='" + id + "'"
        );
    }

    @SneakyThrows
    public static <T extends Entity> PreparedStatement statementForFindingById(Connection connection, UUID id, Class<T> clazz) {
        return connection.prepareStatement(
                "SELECT " + mergeFieldNames(clazz) + " FROM " + clazz.getSimpleName().toLowerCase()
                        + "  WHERE id = '" + id + "'"
        );
    }

    private static <T> String mergeFieldNameToValue(T object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .map(field -> field.getName().toLowerCase() + "=" + mapFieldValue(object, field))
                .collect(Collectors.joining(","));
    }

    private static <T> String mergeFieldValues(T object) {
        return "(" + Arrays.stream(object.getClass().getDeclaredFields())
                .map(field -> mapFieldValue(object, field))
                .collect(Collectors.joining(",")) + ")";
    }

    private static <T> String mergeFieldNames(Class<T> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .map(Field::getName)
                .map(String::toLowerCase)
                .collect(Collectors.joining(","));
    }

    @SneakyThrows
    private static <T> String mapFieldValue(T object, Field field) {
        field.setAccessible(true);

        if (UUID.class.equals(field.getType())) {
            return encloseWithQuotes(field.get(object).toString());
        } else if (Instant.class.equals(field.getType())) {
            return encloseWithQuotes(new java.sql.Timestamp(((Instant) field.get(object)).toEpochMilli()));
        }

        Object result = field.get(object);

        return result instanceof Number
                ? result.toString()
                : encloseWithQuotes(result);
    }

    private static String encloseWithQuotes(Object object) {
        return "'" + object + "'";
    }
}
