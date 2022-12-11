package service;

import dao.CourseDao;
import dto.CourseDto;
import entity.Course;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CourseService {

    private final static CourseService INSTANCE = new CourseService();

    private final CourseDao courseDao = CourseDao.getInstance();

    public List<CourseDto> findAllByUserId(Long userId) {
        return courseDao.findAllByUserId(userId)
                .stream()
                .map(course -> new CourseDto(
                        course.getId(),
                        course.getName(),
                        course.getDescription()

                )).collect(toList());
    }

    public static CourseService getInstance() {
        return INSTANCE;
    }
}
