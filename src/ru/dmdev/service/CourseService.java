package ru.dmdev.service;

import ru.dmdev.dao.CourseDao;
import ru.dmdev.dto.CourseDto;

import java.util.List;

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
