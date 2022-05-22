package com.epam.finaltask.onlinetraining.servlets;

import com.epam.finaltask.onlinetraining.domain.Role;
import com.epam.finaltask.onlinetraining.domain.User;
import com.epam.finaltask.onlinetraining.exceptions.UserHasBeenFoundException;
import com.epam.finaltask.onlinetraining.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.EMAIL;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.FIRST_NAME;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.LAST_NAME;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.PASSWORD;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.ROLE;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.ROLES;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_REGISTRATION_PAGE;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_SLASH_SERVLET_PATH;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService;

    public RegistrationServlet() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Role[] roles = Role.values();
        session.setAttribute(ROLES, roles);
        resp.sendRedirect(String.format(FORMAT_REGISTRATION_PAGE, req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            userService.registerUser(
                    new User.Builder()
                            .withRole(Role.valueOf(req.getParameter(ROLE).toUpperCase()))
                            .withFirstName(req.getParameter(FIRST_NAME).toUpperCase())
                            .withLastName(req.getParameter(LAST_NAME).toUpperCase())
                            .withEmail(req.getParameter(EMAIL).toUpperCase())
                            .withPassword(req.getParameter(PASSWORD))
                            .build()
            );
        } catch (SQLException | UserHasBeenFoundException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(String.format(FORMAT_SLASH_SERVLET_PATH, req.getContextPath()));
    }
}