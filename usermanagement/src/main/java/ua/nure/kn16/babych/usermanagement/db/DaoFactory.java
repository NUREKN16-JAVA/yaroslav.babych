package ua.nure.kn16.babych.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {

    protected static final String DAO_FACTORY = "dao.factory";
    protected static Properties properties;
    private static DaoFactory instance;


    static {
        properties = new Properties();
        try {
            properties.load(DaoFactory.class.getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };


    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            try {
                Class factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
                instance = (DaoFactory) factoryClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public static void init(Properties properties2) {
        properties = properties2;
        instance = null;
    }


    protected ConnectionFactory getConnectionFactory() {
        String user = properties.getProperty("connection.user"),
                password = properties.getProperty("connection.password"),
                url = properties.getProperty("connection.url"),
                driver = properties.getProperty("connection.driver");
        return new ConnectionFactoryImpl(driver, url, user, password);
    }

    public abstract UserDAO getUserDao();
}
