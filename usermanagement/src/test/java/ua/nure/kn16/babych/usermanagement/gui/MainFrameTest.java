package ua.nure.kn16.babych.usermanagement.gui;


import com.mockobjects.dynamic.Mock;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.kn16.babych.usermanagement.User;
import ua.nure.kn16.babych.usermanagement.db.DaoFactory;
import ua.nure.kn16.babych.usermanagement.db.MockDaoFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class MainFrameTest extends JFCTestCase {

    private Mock mockUserDao;
    private List<User> users;
    private MainFrame mainFrame;

    protected void setUp() throws Exception {
        super.setUp();
        Properties properties = new Properties();
        properties.setProperty("dao.factory", MockDaoFactory.class.getName());
        DaoFactory.init(properties);
        mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();
        User expectedUser = new User(1000L, "George", "Bush", new Date());

        users = Collections.singletonList(expectedUser);
        mockUserDao.expectAndReturn("findAll", users);
        setHelper(new JFCTestHelper());

        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    protected void tearDown() throws Exception {
        //mockUserDao.verify();
        mainFrame.setVisible(false);
        getHelper();
        TestHelper.cleanUp(this);
        super.tearDown();
    }

    private Component find(Class<?> componentClass, String name) {
        NamedComponentFinder finder;
        finder = new NamedComponentFinder(componentClass, name);
        finder.setWait(0);
        Component component = finder.find(mainFrame, 0);
        assertNotNull("Could not find component '" + name + "'",component);
        return  component;
    }

    public void testBrowseControls() {
        find(JPanel.class, "browsePanel");
        JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(3, table.getColumnCount());
        // TODO Localize test
        assertEquals("id", table.getColumnName(0));
        assertEquals("First Name", table.getColumnName(1));
        assertEquals("Last Name", table.getColumnName(2));

        find(JButton.class, "addButton");
        find(JButton.class, "editButton");
        find(JButton.class, "deleteButton");
        find(JButton.class, "detailsButton");
    }
}
