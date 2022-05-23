package com.epam.finaltask.onlinetraining.servlets.adminServlets;

import com.epam.finaltask.onlinetraining.domain.User;
import com.epam.finaltask.onlinetraining.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.USERS;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_ADMIN_MENU;

@WebServlet("/access/adminView")
public class AdminMenuServlet extends HttpServlet {
    private final UserService userService;

    public AdminMenuServlet() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            HttpSession session = req.getSession();
            List<User> users = userService.getAllUser();
            session.setAttribute(USERS, users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(String.format(FORMAT_ADMIN_MENU, req.getContextPath()));
    }
}