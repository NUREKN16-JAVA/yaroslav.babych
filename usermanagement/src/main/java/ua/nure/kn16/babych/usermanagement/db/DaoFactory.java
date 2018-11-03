package ua.nure.kn16.babych.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {
    private final Properties properties;
    private UserDAO result = null;
    private final static DaoFactory INSTANCE = new DaoFactory();

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    private DaoFactory() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionFactory getConnectionFactory() {
        String user = properties.getProperty("connection.user"),
                password = properties.getProperty("connection.password"),
                url = properties.getProperty("connection.url"),
                driver = properties.getProperty("connection.driver");
        return new ConnectionFactoryImpl(driver, url, user, password);
    }

    public UserDAO getUserDAO() {
        try {
            Class c = Class.forName(properties.getProperty("dao.ua.nure.kn16.babych.usermanagement.db.UserDAO"));
            result = (UserDAO) c.newInstance();
            result.setConnectionFactory(getConnectionFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
