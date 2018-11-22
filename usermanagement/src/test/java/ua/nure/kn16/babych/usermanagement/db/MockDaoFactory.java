package ua.nure.kn16.babych.usermanagement.db;

import com.mockobjects.dynamic.Mock;

public class MockDaoFactory extends DaoFactory{

    private Mock mockUserDao;

    public MockDaoFactory() {
        mockUserDao = new Mock(UserDAO.class);
    }


    public  Mock getMockUserDao() {
        return  mockUserDao;
    }

    @Override
    public UserDAO getUserDao() {
        return (UserDAO) mockUserDao.proxy();
    }
}
