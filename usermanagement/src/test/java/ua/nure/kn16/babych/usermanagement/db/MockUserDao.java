package ua.nure.kn16.babych.usermanagement.db;

import ua.nure.kn16.babych.usermanagement.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockUserDao implements UserDAO {
    private long id = 0;
    private Map users = new HashMap();

    @Override
    public User create(User user) throws DBException {
        Long currentId = new Long(++id);
        user.setId(currentId);
        users.put(currentId, user);
        return user;
    }

    @Override
    public User find(Long id) throws DBException {
        return (User) users.get(id);
    }

    @Override
    public Collection<User> findAll() throws DBException {
        return users.values();
    }

    @Override
    public void update(User user) throws DBException {
        Long currentId = user.getId();
        users.remove(currentId);
        users.put(currentId,user);

    }

    @Override
    public void delete(Long currentId) throws DBException {
        users.remove(currentId);
    }

    @Override
    public Collection<User> findByName(String firstName, String lastName) throws DBException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ConnectionFactory getConnectionFactory() {
        return null;
    }

    @Override
    public void setConnectionFactory(ConnectionFactory connectionFactory) {

    }
}