package com.epam.finaltask.onlinetraining.servlets;

import com.epam.finaltask.onlinetraining.domain.User;
import com.epam.finaltask.onlinetraining.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.*;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_ERROR_HANDLER_PATH;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_SLASH_SERVLET_PATH;
import static java.util.Objects.nonNull;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    private final UserService userService;

    public LogInServlet() {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        String authEmail = getAuthEmail(req);
        String authPassword = req.getParameter(AUTH_PASSWORD);

        if (userService.isUserAuth(authEmail, authPassword)) {
            User user = userService.getUserByEmail(authEmail);

            String userName = String.format("%s %s", user.getFirstName(), user.getLastName());
            session.setAttribute(AUTH_EMAIL, authEmail);
            session.setAttribute(AUTH_PASSWORD, authPassword);
            session.setAttribute(AUTH_ROLE, user.getRole());
            session.setAttribute(USER_NAME, userName);

            resp.sendRedirect(String.format(FORMAT_SLASH_SERVLET_PATH, req.getContextPath()));
        } else {
            resp.sendRedirect(String.format(FORMAT_ERROR_HANDLER_PATH, req.getContextPath()));
        }
    }

    private String getAuthEmail(HttpServletRequest req) {
        return nonNull(req.getParameter(AUTH_EMAIL)) ? req.getParameter(AUTH_EMAIL).toUpperCase() : null;
    }
}