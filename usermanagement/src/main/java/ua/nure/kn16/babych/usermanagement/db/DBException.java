package ua.nure.kn16.babych.usermanagement.db;

import java.sql.SQLException;

public class DBException extends Exception {
    public DBException(SQLException e) {
        super(e);
    }

    public DBException(String s) {
        super(s);
    }
}
