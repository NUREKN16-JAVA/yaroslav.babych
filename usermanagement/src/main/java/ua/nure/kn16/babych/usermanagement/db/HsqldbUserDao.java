package ua.nure.kn16.babych.usermanagement.db;

import ua.nure.kn16.babych.usermanagement.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class HsqldbUserDao implements UserDAO {

    private static final String INSERT_QUERY = "INSERT INTO USERS (FIRSTNAME, LASTNAME, DATEOFBIRTH) VALUES(?, ?, ?)";
    private static final String FIND_QUERY = "SELECT FIRSTNAME, LASTNAME, DATEOFBIRTH FROM USERS WHERE ID = ?";
    private static final String FINDALL_QUERY = "SELECT * FROM USERS";
    private static final String UPDATE_QUERY = "UPDATE USERS SET FIRSTNAME = ?, LASTNAME = ?, DATEOFBIRTH = ? WHERE ID = ?";
    private static final String DELETE_QUERY = "DELETE FROM USERS WHERE ID = ?";

    private static final String CALL_IDENTITY = "call IDENTITY()";
    private ConnectionFactory connectionFactory;

    public HsqldbUserDao() {}

    public HsqldbUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }



    @Override
    public User create(User user) throws DBException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, new Date(user.getDateOfBirth().getTime()));

            int rowsAdded = statement.executeUpdate();
            if (rowsAdded != 1) throw new DBException("Inserted rows: " + rowsAdded);

            CallableStatement callableStatement =
                    connection.prepareCall(CALL_IDENTITY);

            User u = new User(user);

            ResultSet keys = callableStatement.executeQuery();
            if (keys.next()) {
                u.setId(keys.getLong(1));
            }

            keys.close();
            callableStatement.close();
            statement.close();
            connection.close();

            return u;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public User find(Long id) throws DBException {
        try {
            User user;
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString(1);
                String lastName = resultSet.getString(2);
                Date birthDate = resultSet.getDate(3);
                user = new User(id, firstName, lastName, birthDate);
            } else throw new DBException("No users with id " + id + " found");

            resultSet.close();
            statement.close();
            connection.close();
            return user;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<User> findAll() throws DBException {
        try {
            Connection connection = connectionFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FINDALL_QUERY);

            ArrayList<User> users = new ArrayList<>();
            while(resultSet.next()) {
                Long id = resultSet.getLong(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                Date birthDate = resultSet.getDate(4);
                users.add(new User(id, firstName, lastName, birthDate));
            }

            resultSet.close();
            statement.close();
            connection.close();
            return users;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void update(User user) throws DBException{
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
            statement.setLong(4, user.getId());

            int rowsModified = statement.executeUpdate();
            if (rowsModified != 1) throw new DBException("Modified rows: " + rowsModified);

            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void delete(Long id) throws DBException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setString(1, id.toString());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted != 1) throw new DBException("Deleted rows: " + rowsDeleted);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    @Override
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
