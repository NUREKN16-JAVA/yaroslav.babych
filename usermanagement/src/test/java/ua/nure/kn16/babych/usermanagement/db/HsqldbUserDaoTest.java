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


    /**
     * Test create function
     * @throws DBException
     */
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

    /**
     * Test find function
     * @throws DBException
     */
    @Test
    public void testFind() throws DBException {
        User userResult = dao.find(0L);
        assertNotNull(userResult);
        assertEquals("Petr", userResult.getFirstName());
    }

    @Test
    public void testFindAll() throws DBException {
        Collection all = dao.findAll();
        assertNotNull(all);
        assertEquals(1, all.size());
    }

    /**
     * Test update function, assuming find function has already been tested
     * @throws DBException
     */
    @Test
    public void testUpdate() throws DBException {
        User tuser = new User(0L, "A", "BCD", new Date());
        dao.update(tuser);
        User user2 = dao.find(tuser.getId());
        assertNotNull(user2);
        assertEquals(tuser.getLastName(), user2.getLastName());
    }

    /**
     * Test delete function, assuming findAll function has already been tested
     * @throws DBException
     */
    @Test
    public void testDelete() throws DBException {
        dao.delete(1L);
        assertEquals(1, dao.findAll().size());
    }
}