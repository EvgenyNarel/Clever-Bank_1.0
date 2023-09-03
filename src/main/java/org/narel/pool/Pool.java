package org.narel.pool;

import java.sql.Connection;

/**
 *
 */
public interface Pool extends AutoCloseable {

    /**
     *
     * @return
     */
    Connection getConnection();


}
