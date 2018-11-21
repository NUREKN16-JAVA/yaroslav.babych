package ua.nure.kn16.babych.usermanagement.db;

import java.sql.Connection;

public interface ConnectionFactory {
    /**
     * Connect to db
     * @return new Connection object
     */
    Connection createConnection() throws DBException;
}
