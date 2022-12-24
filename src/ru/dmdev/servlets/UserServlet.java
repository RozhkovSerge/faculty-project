package ru.dmdev.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.dmdev.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("<h1>Users list:</h1>");
            printWriter.write("<ul></ul>");
            userService.findAll().forEach(userDto ->
                    printWriter.write(
                            """
                                    <li>
                                    <a href="/courses?user_id=%d">%s<a/>,  %s,  %s, Адрес: %s, %s, %s, %s
                                    </li>
                                    """.formatted(
                                            userDto.getId(),
                                            userDto.getFirst_name(),
                                            userDto.getLast_name(),
                                            userDto.getEmail(),
                                            userDto.getAddress().getCity(),
                                            userDto.getAddress().getStreet(),
                                            userDto.getAddress().getHouse(),
                                            userDto.getAddress().getApartment())
                    )
            );
        }
    }
}
