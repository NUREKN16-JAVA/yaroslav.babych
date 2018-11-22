package ua.nure.kn16.babych.usermanagement.db;

import junit.framework.TestCase;
import org.dbunit.DatabaseTestCase;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

public class DaoFactoryTest extends TestCase {

    public void setUp() throws Exception {
    }

    public void testGetUserDAO() {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            assertNotNull(daoFactory);
            UserDAO userDAO = daoFactory.getUserDao();
            assertNotNull(userDAO);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}