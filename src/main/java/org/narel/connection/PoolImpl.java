package org.narel.connection;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.narel.properties.DatasourceProperties;
import org.narel.provider.DatasourcePropertiesProvider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

@RequiredArgsConstructor
public class PoolImpl implements Pool {

    private static final int INITIAL_POOL_SIZE = 10;
    private static final DatasourceProperties datasourceProperties = new DatasourcePropertiesProvider().propertiesObject();

    private final ArrayBlockingQueue<Connection> wrappedConnections = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
    private final List<Connection> originalConnections = new ArrayList<>(INITIAL_POOL_SIZE);

    @SneakyThrows
    public static Pool newPool() {
        PoolImpl pool = new PoolImpl();

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            Connection connection = connection();
            pool.wrappedConnections.add(wrappedConnection(pool, connection));
            pool.originalConnections.add(connection);
        }

        return pool;
    }

    @SneakyThrows
    @Override
    public Connection getConnection() {
        return wrappedConnections.take();
    }

    private static Connection wrappedConnection(PoolImpl pool, Connection connection) {
        return (Connection) Proxy.newProxyInstance(PoolImpl.class.getClassLoader(),
                new Class[]{Connection.class},
                pool.new ConnectionInvocationHandler(connection)
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
    private class ConnectionInvocationHandler implements InvocationHandler {

        private final Connection connection;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("close".equals(method.getName())) {
                wrappedConnections.put((Connection) proxy);
                return null;
            }

            return method.invoke(connection, args);
        }
    }
}
