package dao;

import entity.Address;
import exeption.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AddressDao {

    private final static String FIND_BY_ID_SQL = """
            SELECT
            id,
            city,
            street,
            house,
            apartment
            FROM faculty.address WHERE id = ?
            """;

    public Optional<Address> findOneById(Long id) {
        Address address = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
             preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                address = new Address(
                        resultSet.getLong("id"),
                        resultSet.getString("city"),
                        resultSet.getString("street"),
                        resultSet.getString("house"),
                        resultSet.getString("apartment")
                );
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(address);
    }
}
