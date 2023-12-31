package org.narel.utils;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class ResultSetMapper {

    @SneakyThrows
    public static <T> List<T> mapObjects(ResultSet resultSet, Class<T> clazz) {
        List<T> result = new ArrayList<>();

        while (resultSet.next()) {
            result.add(toObject(resultSet, clazz));
        }

        return result;
    }

    @SneakyThrows
    public static <T> T mapObject(ResultSet resultSet, Class<T> clazz) {
        T result = null;

        if (resultSet.next()) {
            result = toObject(resultSet, clazz);
        }

        return result;

    }

    @SneakyThrows
    private static <T> T toObject(ResultSet resultSet, Class<T> clazz) {
        T result;
        result = clazz.getDeclaredConstructor().newInstance();
        Map<String, Field> fields = getFields(clazz);

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            String columnName = resultSetMetaData.getColumnName(i).toLowerCase();
            Field field = fields.get(columnName);
            if (field != null) {
                field.setAccessible(true);
                fillField(result, field, resultSet.getObject(columnName));
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    private static <T> void fillField(T result, Field field, Object dbObject) {
        if (field.getType().isEnum()) {
            field.set(result, Enum.valueOf((Class<Enum>) field.getType(), dbObject.toString()));
        } else if (Instant.class.equals(field.getType())) {
            field.set(result, Instant.ofEpochMilli(((Timestamp)dbObject).getTime()));
        } else {
            field.set(result, dbObject);
        }
    }

    private static <T> Map<String, Field> getFields(Class<T> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toMap(field -> field.getName().toLowerCase(), Function.identity()));
    }
}