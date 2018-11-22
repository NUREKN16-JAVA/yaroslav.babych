package ua.nure.kn16.babych.usermanagement.db;

public class DaoFactoryImpl extends DaoFactory {

    public UserDAO getUserDao() {
        UserDAO result = null;
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
