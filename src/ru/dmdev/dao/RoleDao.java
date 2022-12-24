package ru.dmdev.dao;

import ru.dmdev.entity.Role;
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

public class RoleDao implements Dao<Long, Role> {
    private static final RoleDao INSTANCE = new RoleDao();
    private static final String SAVE_SQL = """
            INSERT INTO faculty.roles (name) VALUES (?)
            """;

    private final static String FIND_BY_ID_SQL = """
            SELECT id, name FROM faculty.roles WHERE id = ?
            """;

    private final static String FIND_ALL_SQL = """
            SELECT id, name FROM faculty.roles
            """;
    private static final String UPDATE_SQL = """
            UPDATE faculty.roles SET name=? WHERE id=?
            """;

    private static final String DELETE_SQL = """
            DELETE from faculty.roles WHERE id=?
            """;

    public Role save(Role role) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                role.setId(generatedKeys.getLong("id"));
            }
            return role;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Role> findById(Long id) {

        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            Role role = null;
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = new Role(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
            }
            return Optional.ofNullable(role);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Role> findAll() {
        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Role> roles = new ArrayList<>();
            while(resultSet.next()) {
                roles.add(new Role(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                ));
            }
            return roles;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean update(Role role) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.setLong(2, role.getId());

            return preparedStatement.executeUpdate() > 0;

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

    public static RoleDao getInstance() {
        return INSTANCE;
    }
}
