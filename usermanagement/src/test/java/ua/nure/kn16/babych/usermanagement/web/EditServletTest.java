package ua.nure.kn16.babych.usermanagement.web;

import ua.nure.kn16.babych.usermanagement.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditServletTest extends MockServletTestCase {
    private static final String NAME = "John";
    private static final String LAST_NAME = "Doe";
    public static final String ID = "1000";
    private DateFormat df = new SimpleDateFormat("yyyy.MM.dd");

    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(EditServlet.class);
    }

    public void testEdit(){
        Date date = new Date();
        User user = new User(1000L, NAME, LAST_NAME, date);
        getMockUserDao().expect("update", user);

        addRequestParameter("id", ID);
        addRequestParameter("firstName", NAME);
        addRequestParameter("lastName", LAST_NAME);

        addRequestParameter("date", df.format(date));
        addRequestParameter("okButton", "Ok");
        doPost();

    }

    public void testEditEmptyFirstName(){
        Date date = new Date();

        addRequestParameter("id", ID);
        addRequestParameter("lastName", LAST_NAME);
        addRequestParameter("date", df.format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testEditEmptyLastName(){
        Date date = new Date();

        addRequestParameter("id", ID);
        addRequestParameter("firstName", NAME);
        addRequestParameter("date", df.format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testEditEmptyDate(){
        addRequestParameter("id", ID);
        addRequestParameter("firstName", NAME);
        addRequestParameter("lastName", LAST_NAME);
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testEditEmptyDateIncorrect(){
        addRequestParameter("id", ID);
        addRequestParameter("firstName", NAME);
        addRequestParameter("lastName", LAST_NAME);
        addRequestParameter("date", "asdnbasfkjsdfhkahjf");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
}
