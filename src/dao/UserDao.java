package dao;

import entity.Address;
import entity.Role;
import entity.User;
import exeption.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class UserDao {
    private static final UserDao INSTANCE = new UserDao();
    private static final String DELETE_SQL = """
            DELETE FROM faculty.users WHERE id=?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO faculty.users(first_name, last_name, email, password, address, role) VALUES (?,?,?,?,?,?)
            """;
    private static final String UPDATE_SQL = """
            UPDATE faculty.users SET
            first_name = ?,
            last_name = ?,
            email = ?,
            password = ?,
            address = ?,
            role = ?
            WHERE id = ?
            """;
    private static final String FIND_BY_ID_SQL = """
            SELECT 
            id, 
            first_name, 
            last_name, 
            email, 
            password, 
            address, 
            role 
            FROM faculty.users 
            WHERE id = ?
            """;

    private UserDao() {
    }

    public Optional<User> findOneById(Long id) {
        User user = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Address address = addressDao.findOneById(resultSet.getLong("address"));
                Role role = roleDao.findOneById(resultSet.getLong("role"));
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        address,
                        role
                );
            }

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public User save(User user) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getLast_name());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setObject(5, user.getAddress().getId());
            preparedStatement.setObject(6, user.getRole().getId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void update(User user) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getLast_name());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setObject(5, user.getAddress().getId());
            preparedStatement.setObject(6, user.getRole().getId());
            preparedStatement.setLong(7, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public static UserDao getInstance() {
        return INSTANCE;
    }
}
