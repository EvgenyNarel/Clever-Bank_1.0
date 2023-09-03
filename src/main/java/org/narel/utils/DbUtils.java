package org.narel.utils;

import org.narel.properties.DatasourceProperties;
import org.narel.provider.DatasourcePropertiesProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    private final static DatasourceProperties datasourceProperties;

    static  {
        DatasourcePropertiesProvider datasourcePropertiesProvider = new DatasourcePropertiesProvider();
        datasourceProperties = datasourcePropertiesProvider.propertiesObject();
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(datasourceProperties.getDriverClassName());
            connection = DriverManager.getConnection(datasourceProperties.getUrl(), datasourceProperties.getUser(), datasourceProperties.getPassword());

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
        return connection;
    }
}

