package dao;

import entity.Archive;
import exeption.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArchiveDao {
    private static final ArchiveDao INSTANCE = new ArchiveDao();

    private final String SAVE_SQL = """
            INSERT INTO faculty.archive (
            user_id,
            course_id,
            mark,
            date_of_exam
            )
            VALUES(?,?,?,?)
            """;

    private final String FIND_BY_ID_SQL = """
            SELECT id, user_id, course_id, mark, date_of_exam
            FROM faculty.archive
            WHERE id=?
            """;

    private final String FIND_ALL_SQL = """
            SELECT id, user_id, course_id, mark, date_of_exam
            FROM faculty.archive
            """;

    private final String UPDATE_SQL = """
            UPDATE faculty.archive
            SET
            user_id=?,
            course_id=?,
            mark=?,
            date_of_exam=?
            WHERE id=?
            """;

    private final String DELETE_SQL = """
            DELETE FROM faculty.archive
            WHERE id=?
            """;

    public Archive save(Archive archive) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, archive.getUserId());
            preparedStatement.setLong(2, archive.getCourseId());
            preparedStatement.setLong(3, archive.getMark());
            preparedStatement.setDate(4, new Date(archive.getDateOfExam().getTime()));
            preparedStatement.executeUpdate();

            if (preparedStatement.getGeneratedKeys().next()) {
                archive.setId(preparedStatement.getGeneratedKeys().getLong("id"));
            }
            return archive;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Archive> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Archive archive = null;
            if (resultSet.next()) {
                archive = new Archive(
                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getLong("course_id"),
                        resultSet.getInt("mark"),
                        resultSet.getDate("date_of_exam")
                );
            }
            return Optional.ofNullable(archive);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Archive> findAll() {
        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Archive> archives = new ArrayList<>();
            while (resultSet.next()) {
                archives.add(new Archive(
                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getLong("course_id"),
                        resultSet.getInt("mark"),
                        resultSet.getDate("date_of_exam")
                ));
            }
            return archives;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    public boolean update (Archive archive) {
        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, archive.getUserId());
            preparedStatement.setLong(2, archive.getCourseId());
            preparedStatement.setInt(3, archive.getMark());
            preparedStatement.setDate(4, new Date(archive.getDateOfExam().getTime()));
            preparedStatement.setLong(5, archive.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Long id) {
        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public static ArchiveDao getInstance() {
        return INSTANCE;
    }
}
