package dao;

import entity.Address;
import exeption.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class AddressDao {

    private final static AddressDao INSTANCE = new AddressDao();

    private final static String SAVE_SQL = """
            INSERT INTO faculty.address (city, street, house, apartment) VALUES (?,?,?,?);
            """;
    private final static String FIND_BY_ID_SQL = """
            SELECT
            id,
            city,
            street,
            house,
            apartment
            FROM faculty.address WHERE id = ?
            """;

    public Address save(Address address) {
        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getHouse());
            preparedStatement.setString(4, address.getApartment());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                address.setId(generatedKeys.getLong("id"));
            }

            return address;

        } catch(SQLException e) {
            throw new DaoException(e);
        }
    }

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

    public static AddressDao getInstance() {
        return INSTANCE;
    }
}
