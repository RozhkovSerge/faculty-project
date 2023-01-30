package ru.dmdev.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.dmdev.service.CourseService;
import ru.dmdev.util.JspHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {

    private final CourseService courseService = CourseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long user_id = Long.valueOf(req.getParameter("user_id"));
        req.setAttribute("courses", courseService.findAllByUserId(user_id));
        req.getRequestDispatcher(JspHelper.getPath("courses")).forward(req, resp);
    }
}

