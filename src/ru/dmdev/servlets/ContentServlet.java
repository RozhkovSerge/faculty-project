package ru.dmdev.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.dmdev.dto.UserDto;
import ru.dmdev.service.UserService;
import ru.dmdev.util.JspHelper;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toMap;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDto> userDtos = userService.findAll();
        req.setAttribute("users", userDtos);
        req.getSession().setAttribute("usersMap", userDtos.stream().collect(toMap(UserDto::getId, UserDto::getFirstName)));
        req.getRequestDispatcher(JspHelper.getPath("content")).forward(req, resp);
    }
}
