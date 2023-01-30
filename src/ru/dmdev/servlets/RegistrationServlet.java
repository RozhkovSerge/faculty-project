package ru.dmdev.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.dmdev.dto.AddressDto;
import ru.dmdev.dto.CreateUserDto;
import ru.dmdev.service.UserService;
import ru.dmdev.util.JspHelper;

import java.io.IOException;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", List.of("USER", "ADMIN"));
        req.setAttribute("genders", List.of("Male", "Female"));
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddressDto addressDto = AddressDto
                .builder()
                .city(req.getParameter("city"))
                .street(req.getParameter("street"))
                .house(req.getParameter("house"))
                .apartment(req.getParameter("apartment"))
                .build();

        CreateUserDto userDto = CreateUserDto
                .builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .address(addressDto)
                .role(req.getParameter("role"))
                .build();

        userService.create(userDto);
        resp.sendRedirect("/login");
    }

}
