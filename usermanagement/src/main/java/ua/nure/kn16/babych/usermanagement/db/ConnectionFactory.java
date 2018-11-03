package ua.nure.kn16.babych.usermanagement.db;

import java.sql.Connection;

public interface ConnectionFactory {
    /**
     *
     * @return
     */
    Connection createConnection() throws DBException;
}
