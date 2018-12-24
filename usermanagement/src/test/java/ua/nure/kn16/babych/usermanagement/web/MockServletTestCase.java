package ua.nure.kn16.babych.usermanagement.web;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import ua.nure.kn16.babych.usermanagement.db.DaoFactory;
import ua.nure.kn16.babych.usermanagement.db.MockDaoFactory;

import java.util.Properties;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

    public Mock getMockUserDao() {
        return mockUserDao;
    }

    public void setMockUserDao(Mock mockUserDao) {
        this.mockUserDao = mockUserDao;
    }

    private Mock mockUserDao;



    public void setUp() throws Exception {
        super.setUp();
        Properties properties = new Properties();
        properties.setProperty("dao.factory", MockDaoFactory.class.getName());
        DaoFactory.init(properties);
        setMockUserDao(((MockDaoFactory)DaoFactory.getInstance()).getMockUserDao());
    }



    public void tearDown() throws Exception {
        getMockUserDao().verify();
        super.tearDown();
    }


}
