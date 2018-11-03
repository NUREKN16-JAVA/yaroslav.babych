package ua.nure.kn16.babych.usermanagement.db;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.nure.kn16.babych.usermanagement.User;

import java.util.Collection;
import java.util.Date;

public class HsqldbUserDaoTest extends DatabaseTestCase {

    private User user;
    private UserDAO dao;
    private ConnectionFactory connectionFactory;
    /**
    /* Tests are WIP and will be improved in the future
     */
    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        //connectionFactory = new ConnectionFactoryImpl();
        return new DatabaseConnection(connectionFactory.createConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new XmlDataSet(getClass().getClassLoader()
                .getResourceAsStream("usersDataSet.xml"));
        return dataSet;
    }

    @Before
    public void setUp() throws Exception {
        //getConnection();
        dao = DaoFactory.getInstance().getUserDAO();
        user = new User("Ivan", "Ivanov", new Date());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreate() throws DBException {
        assertNull(user.getId());
        User userResult = dao.create(user);
        assertNotNull(userResult);
        assertNotNull(userResult.getId());
        assertEquals(user.getFirstName(), userResult.getFirstName());
        assertEquals(user.getLastName(), userResult.getLastName());
        assertEquals(user.getDateOfBirth(), userResult.getDateOfBirth());
    }

    @Test
    public void testFind() throws DBException {
        User userResult = dao.find(1L);
        assertNotNull(userResult);
        assertEquals("A", userResult.getFirstName());
    }

    @Test
    public void testFindAll() throws DBException {
        User userResult = dao.create(user);
        Collection all = dao.findAll();
        assertNotNull(all);
        assertEquals(2, all.size());
    }

    @Test
    public void testUpdate() throws DBException {
        User user = new User(1L, "A", "BCD", new Date());
        dao.update(user);
        User user2 = dao.find(user.getId());
        assertNotNull(user2);
        assertEquals(user.getLastName(), user2.getLastName());
    }

    @Test
    public void testDelete() throws DBException {
        dao.delete(1L);
        assertEquals(0, dao.findAll().size());
    }
}