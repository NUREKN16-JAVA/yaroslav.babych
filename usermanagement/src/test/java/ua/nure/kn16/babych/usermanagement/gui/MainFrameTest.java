package ua.nure.kn16.babych.usermanagement.gui;


import com.mockobjects.dynamic.Mock;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.kn16.babych.usermanagement.User;
import ua.nure.kn16.babych.usermanagement.db.DaoFactory;
import ua.nure.kn16.babych.usermanagement.db.MockDaoFactory;
import ua.nure.kn16.babych.usermanagement.util.Message;

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

        assertEquals(Message.getString("id"), table.getColumnName(0));
        assertEquals(Message.getString("firstname"), table.getColumnName(1));
        assertEquals(Message.getString("lastname"), table.getColumnName(2));

        find(JButton.class, "addButton");
        find(JButton.class, "editButton");
        find(JButton.class, "deleteButton");
        find(JButton.class, "detailsButton");
    }




    public void testAddUser() {

        JTable userTable = (JTable) find(JTable.class, "userTable");
        assertEquals(1, userTable.getRowCount());

        JButton addButton = (JButton) find(JButton.class, "addButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
        find(JPanel.class, "addPanel");
        JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
        JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
        JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");

        JButton okButton = (JButton) find(JButton.class, "okButton");
        find(JButton.class, "cancelButton");

        getHelper().sendString(new StringEventData(this, firstNameField, "Vanya"));
        getHelper().sendString(new StringEventData(this, lastNameField, "Ivanov"));
        getHelper().sendString(new StringEventData(this, dateOfBirthField, "11.11.2011"));
        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

        find(JPanel.class, "browsePanel");
        userTable = (JTable) find(JTable.class, "userTable");
        assertEquals(1, userTable.getRowCount());
    }

    private void testEditUser() {

    }
}
