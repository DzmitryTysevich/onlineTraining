package com.epam.finaltask.onlinetraining.servlets.studentServlet;

import com.epam.finaltask.onlinetraining.domain.User;
import com.epam.finaltask.onlinetraining.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.AUTH_EMAIL;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.USER;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_STUDENT_MENU;

@WebServlet("/access/studentView")
public class StudentMenuServlet extends HttpServlet {
    private final UserService userService;

    public StudentMenuServlet() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String authEmail = (String) session.getAttribute(AUTH_EMAIL);
        User user = userService.getUserByEmail(authEmail);
        session.setAttribute(USER, user);

        resp.sendRedirect(String.format(FORMAT_STUDENT_MENU, req.getContextPath()));
    }
}