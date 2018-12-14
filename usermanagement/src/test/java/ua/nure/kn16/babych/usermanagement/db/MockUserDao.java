package ua.nure.kn16.babych.usermanagement.db;

import ua.nure.kn16.babych.usermanagement.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockUserDao implements UserDAO {

    private long id = 0;
    private HashMap<Long, User> users = new HashMap<>();

    @Override
    public User create(User user) throws DBException {
        Long currentId = id++;
        user.setId(currentId);
        users.put(currentId, user);
        return user;
    }

    @Override
    public User find(Long id) throws DBException {
        User result = users.get(id);
        if (result == null) throw new DBException("User not found");
        return result;
    }

    @Override
    public List<User> findAll() throws DBException {
        return new ArrayList(users.values());
    }

    @Override
    public void update(User user) throws DBException {
        if (!users.containsKey(user.getId()))
            throw new DBException("User not found");
        users.put(user.getId(), user);
    }

    @Override
    public void delete(Long user_id) throws DBException {
        if (!users.containsKey(user_id))
            throw new DBException("User not found");
        users.remove(user_id);
    }

    @Override
    public ConnectionFactory getConnectionFactory() {
        return null;
    }

    @Override
    public void setConnectionFactory(ConnectionFactory connectionFactory) {

    }
}
