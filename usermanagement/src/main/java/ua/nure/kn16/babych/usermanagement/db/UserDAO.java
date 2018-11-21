package ua.nure.kn16.babych.usermanagement.db;

import com.sun.istack.internal.NotNull;
import ua.nure.kn16.babych.usermanagement.User;

import java.util.List;

public interface UserDAO {
    /**
     * Add a new user to DB table USER
     * @param user with null id field
     * @return new user record  with auto-generated id
     */
    public User create(@NotNull final User user) throws DBException;

    /**
     * Find a user by id
     * @param id of a user
     * @return User object with specified id
     * if there's no such user, throw DBException
     */
    public User find(final Long id) throws DBException;

    /**
     * Find all users in DB
     * @return list of all users
     */
    public List<User> findAll() throws DBException;

    /**
     * Update user's info in DB
     * Find a user record in db with id equal to
     * @param user
     * and set its firstname lastname and birthdate
     */
    public void update(final User user) throws DBException;

    /**
     * Delete a user from db
     * @param id of a user to delete
     */
    public void delete(final Long id) throws DBException;

    public ConnectionFactory getConnectionFactory();

    /**
     *
     * @param connectionFactory
     */
    public void setConnectionFactory(ConnectionFactory connectionFactory);
}
