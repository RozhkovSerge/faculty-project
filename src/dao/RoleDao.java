package dao;

import entity.Role;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RoleDao {

    private final static String FIND_BY_ID_SQL = """
            SELECT id, name FROM faculty.user_roles WHERE id = ?
            """;

    // TODO: 29.11.2022 implement method
    public Optional<Role> findOneById(Long id) {

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
}
