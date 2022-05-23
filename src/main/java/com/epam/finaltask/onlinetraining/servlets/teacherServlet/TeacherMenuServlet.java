package com.epam.finaltask.onlinetraining.servlets.teacherServlet;

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
import java.util.Objects;

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.AUTH_EMAIL;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.USERS;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_TEACHER_MENU;

@WebServlet("/access/teacherView")
public class TeacherMenuServlet extends HttpServlet {
    private final UserService userService;

    public TeacherMenuServlet() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        try {
            String email = (String) session.getAttribute(AUTH_EMAIL);
            User user = userService.getUserByEmail(email);
            if (Objects.nonNull(user.getCourse())) {
                List<User> users = userService.getUsersByCourse(user.getCourse().getId());
                session.setAttribute(USERS, users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(String.format(FORMAT_TEACHER_MENU, req.getContextPath()));
    }
}