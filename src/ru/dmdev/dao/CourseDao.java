package ru.dmdev.dao;

import ru.dmdev.entity.Course;
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

public class CourseDao implements Dao<Long, Course> {

    private static final CourseDao INSTANCE = new CourseDao();

    private final String SAVE_SQL = """
            INSERT INTO faculty.course (
            name,
            description
            )
            VALUES(?,?)
            """;

    private final String FIND_BY_ID_SQL = """
            SELECT id, name, description
            FROM faculty.course
            WHERE id=?
            """;

    private final String FIND_ALL_SQL = """
            SELECT id, name, description
            FROM faculty.course
            """;

    private final String FIND_ALL_BY_USER_ID_SQL = """
            SELECT id, name, description
            FROM faculty.course WHERE id in
                (SELECT course_id from faculty.users_courses where user_id=?)
            """;

    private final String UPDATE_SQL = """
            UPDATE faculty.course SET name=?, description=?
            WHERE id=?
            """;

    private final String DELETE_SQL = """
            DELETE FROM faculty.course
            WHERE id=?
            """;

    public Course save(Course course) {
        try (Connection connection = ConnectionManager.get(); PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                course.setId(generatedKeys.getLong("id"));
            }
            return course;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Course> findById(Long id) {
        Course course = null;
        try (Connection connection = ConnectionManager.get(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                course = new Course(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("description"));
            }
            return Optional.ofNullable(course);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Course> findAll() {
        try (Connection connection = ConnectionManager.get(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                courses.add(new Course(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("description")));
            }
            return courses;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Course> findAllByUserId(Long studentId) {
        try (Connection connection = ConnectionManager.get(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_USER_ID_SQL)) {
            preparedStatement.setLong(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                courses.add(new Course(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("description")));
            }

            return courses;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean update(Course course) {
        try (Connection connection = ConnectionManager.get(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setLong(3, course.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private List<Course> buildCourse(ResultSet resultSet) {
        return null;
    }

    public static CourseDao getInstance() {
        return INSTANCE;
    }
}
