package ru.dmdev.dao;

import ru.dmdev.dto.UserFilter;
import ru.dmdev.entity.Address;
import ru.dmdev.entity.Role;
import ru.dmdev.entity.User;
import ru.dmdev.exeption.DaoException;
import ru.dmdev.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class UserDao implements Dao<Long, User> {

    private final AddressDao addressDao;
    private final RoleDao roleDao;
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

    private static final String FIND_ALL_SQL = """
            SELECT
            id,
            first_name,
            last_name,
            email,
            password,
            address,
            role
            FROM faculty.users
            """;

    private UserDao() {
        addressDao = new AddressDao();
        roleDao = new RoleDao();
    }

    public Optional<User> findById(Long id) {
        User user = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Optional<Address> address = addressDao.findById(resultSet.getLong("address"));
                Optional<Role> role = roleDao.findById(resultSet.getLong("role"));
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        address.orElse(null),
                        role.orElse(null)
                );
            }
            return Optional.ofNullable(user);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<User> findAll(UserFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.firstName() != null) {
            whereSql.add("first_name LIKE ?");
            parameters.add("%" + filter.firstName() + "%");
        }
        if (filter.lastName() != null) {
            whereSql.add("last_name LIKE ?");
            parameters.add("%" + filter.lastName() + "%");
        }
        if (filter.email() != null) {
            whereSql.add("email=?");
            parameters.add(filter.email());
        }
        parameters.add(filter.limit());
        parameters.add(filter.offset());
        String where = whereSql.stream().collect(joining(" AND ", " WHERE ", " LIMIT ? OFFSET ? "));
        String sql = FIND_ALL_SQL + where;
        System.out.println("sql = " + sql);
        System.out.println("where = " + where);
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();

            System.out.println("preparedStatement= " + preparedStatement);
            while (resultSet.next()) {
                Optional<Address> address = addressDao.findById(resultSet.getLong("address"));
                Optional<Role> role = roleDao.findById(resultSet.getLong("role"));
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        address.orElse(null),
                        role.orElse(null)
                ));
            }
            return users;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<User> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                Optional<Address> address = addressDao.findById(resultSet.getLong("address"));
                Optional<Role> role = roleDao.findById(resultSet.getLong("role"));
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        address.stream().findFirst().orElse(null),
                        role.stream().findFirst().orElse(null)
                ));
            }
            return users;

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

    public boolean update(User user) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getLast_name());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setObject(5, user.getAddress().getId());
            preparedStatement.setObject(6, user.getRole().getId());
            preparedStatement.setLong(7, user.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
