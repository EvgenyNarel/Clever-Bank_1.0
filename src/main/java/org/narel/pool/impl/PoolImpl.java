package org.narel.pool.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.narel.pool.Pool;
import org.narel.properties.DatasourceProperties;
import org.narel.provider.impl.DataSourcePropertiesProvider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class PoolImpl implements Pool {

    private static final int INITIAL_POOL_SIZE = 10;
    private static final DatasourceProperties datasourceProperties = new DataSourcePropertiesProvider().propertiesObject();

    private final ArrayBlockingQueue<Connection> wrappedConnections = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
    private final ThreadLocal<Connection> threadConnection = new ThreadLocal<>();
    private final List<Connection> originalConnections = new ArrayList<>(INITIAL_POOL_SIZE);

    private static class PoolImplHandler {
        private final static PoolImpl instance = new PoolImpl();
    }

    public static PoolImpl getInstance() {
        return PoolImplHandler.instance;
    }

    @SneakyThrows
    private PoolImpl() {
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            Connection connection = connection();
            this.wrappedConnections.add(wrappedConnection(connection));
            this.originalConnections.add(connection);
        }
    }

    @SneakyThrows
    @Override
    public Connection getConnection() {
        Connection connection = threadConnection.get();

        if (connection == null) {
            connection = wrappedConnections.take();
            threadConnection.set(connection);
        }

        return connection;
    }

    private Connection wrappedConnection(Connection connection) {
        return (Connection) Proxy.newProxyInstance(PoolImpl.class.getClassLoader(),
                new Class[]{Connection.class},
                new ConnectionWrapper(connection)
        );
    }

    private static Connection connection() throws SQLException {
        return DriverManager.getConnection(
                datasourceProperties.getUrl(),
                datasourceProperties.getUser(),
                datasourceProperties.getPassword()
        );
    }

    @Override
    public void close() {
        originalConnections.forEach(connection -> {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @RequiredArgsConstructor
    private class ConnectionWrapper implements InvocationHandler {

        private final Connection connection;
        private boolean autoCommit = true;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("close".equals(method.getName())) {
                if (!autoCommit) {
                    return null;
                }
                threadConnection.remove();
                wrappedConnections.put((Connection) proxy);
                return null;
            }

            if ("setAutoCommit".equals(method.getName())) {
                this.autoCommit = (Boolean) args[0];
            }

            return method.invoke(connection, args);
        }
    }
}
