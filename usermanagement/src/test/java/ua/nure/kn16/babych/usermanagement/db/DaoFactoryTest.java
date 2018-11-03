package ua.nure.kn16.babych.usermanagement.db;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DaoFactoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getUserDAO() {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            assertNotNull(daoFactory);
            UserDAO userDAO = daoFactory.getUserDAO();
            assertNotNull(userDAO);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}