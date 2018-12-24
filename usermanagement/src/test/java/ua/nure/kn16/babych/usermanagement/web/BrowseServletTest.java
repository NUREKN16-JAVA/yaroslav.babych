package ua.nure.kn16.babych.usermanagement.web;

import ua.nure.kn16.babych.usermanagement.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BrowseServletTest extends MockServletTestCase {

    public static final String NAME = "John";
    public static final String LAST_NAME = "Doe";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBrowse() {
        User testUser = new User(NAME, LAST_NAME, new Date());
        List users = Collections.singletonList(testUser);
        getMockUserDao().expectAndReturn("findAll", users);
        doGet();
        Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
        assertNotNull(collection);
        assertSame(users, collection);
    }

    public void testEdit() {
        User user = new User(1000L, NAME, LAST_NAME, new Date());
        getMockUserDao().expectAndReturn("find", 1000L, user);
        addRequestParameter("editButton", "edit");
        addRequestParameter("id", "1000");
        doPost();
        User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull("Couldn't find user in session", userInSession);
        assertSame(user, userInSession);
    }


}
