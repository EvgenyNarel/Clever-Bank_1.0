package org.narel.connection;

import java.sql.Connection;

public interface Pool extends AutoCloseable {

    Connection getConnection();
}
