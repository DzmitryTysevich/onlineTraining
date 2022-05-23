package com.epam.finaltask.onlinetraining.servlets.studentServlet;

import com.epam.finaltask.onlinetraining.exceptions.UserNotFoundException;
import com.epam.finaltask.onlinetraining.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.*;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_SLASH_SERVLET_PATH;

@WebServlet("/access/studentView/delete")
public class DeleteStudentServlet extends HttpServlet {
    private final UserService userService;

    public DeleteStudentServlet() {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute(AUTH_EMAIL);
        Long id = userService.getUserByEmail(email).getId();
        try {
            userService.deleteUser(id);
            session.removeAttribute(AUTH_EMAIL);
            session.removeAttribute(AUTH_PASSWORD);
            session.removeAttribute(AUTH_ROLE);
            session.removeAttribute(USER_NAME);

            resp.sendRedirect(String.format(FORMAT_SLASH_SERVLET_PATH, req.getContextPath()));
        } catch (SQLException | UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}