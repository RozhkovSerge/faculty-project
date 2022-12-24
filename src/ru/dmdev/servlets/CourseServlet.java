package ru.dmdev.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.dmdev.service.CourseService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {

    private final CourseService courseService = CourseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long user_id = Long.valueOf(req.getParameter("user_id"));

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("<h1>Course list:</h1>");
            printWriter.write("<ul></ul>");
            courseService.findAllByUserId(user_id).forEach(courseDto ->
                    printWriter.write(
                            """
                                    <li>
                                    Курс: %s, %s, %s
                                    </li>
                                    """.formatted(
                                    courseDto.getId(),
                                    courseDto.getName(),
                                    courseDto.getDescription()

                            )
                    )
            );
        }
    }
}

