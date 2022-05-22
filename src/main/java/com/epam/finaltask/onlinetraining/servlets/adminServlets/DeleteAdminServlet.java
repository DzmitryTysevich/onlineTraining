package com.epam.finaltask.onlinetraining.servlets.adminServlets;

import com.epam.finaltask.onlinetraining.exceptions.UserNotFoundException;
import com.epam.finaltask.onlinetraining.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_ADMIN_SERVLET_PATH;

@WebServlet("/access/adminView/delete/users/*")
public class DeleteAdminServlet extends HttpServlet {
    private final UserService userService;

    public DeleteAdminServlet() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getPathInfo().substring(1));
        try {
            userService.deleteUser(id);
            resp.sendRedirect(String.format(FORMAT_ADMIN_SERVLET_PATH, req.getContextPath()));
        } catch (SQLException | UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}