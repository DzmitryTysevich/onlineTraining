package com.epam.finaltask.onlinetraining.servlets.adminServlets;

import com.epam.finaltask.onlinetraining.domain.Course;
import com.epam.finaltask.onlinetraining.domain.Role;
import com.epam.finaltask.onlinetraining.domain.User;
import com.epam.finaltask.onlinetraining.exceptions.UserNotFoundException;
import com.epam.finaltask.onlinetraining.service.CourseService;
import com.epam.finaltask.onlinetraining.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.ASSESSMENT;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.COURSE;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.EMAIL;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.FIRST_NAME;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.ID;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.LAST_NAME;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.PASSWORD;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.REVIEW;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.ROLE;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.ROLES;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.USER_BY_ID;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.ADMIN_UPDATE_PAGE;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_ADMIN_SERVLET_PATH;

@WebServlet("/access/adminView/update/users/*")
public class UpdatableAdminServlet extends HttpServlet {
    private final UserService userService;
    private final CourseService courseService;

    public UpdatableAdminServlet() {
        userService = new UserService();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        try {
            Long id = Long.parseLong(req.getPathInfo().substring(1));
            Role[] roles = Role.values();
            User userById = userService.getUserById(id);
            session.setAttribute(USER_BY_ID, userById);
            session.setAttribute(ROLES, roles);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.getServletContext().getRequestDispatcher(ADMIN_UPDATE_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            userService.updateByAdmin(
                    new User.Builder()
                            .withId(Long.valueOf(req.getParameter(ID)))
                            .withRole(Role.valueOf(req.getParameter(ROLE).toUpperCase()))
                            .withFirstName(req.getParameter(FIRST_NAME).toUpperCase())
                            .withLastName(req.getParameter(LAST_NAME).toUpperCase())
                            .withEmail(req.getParameter(EMAIL).toUpperCase())
                            .withPassword(req.getParameter(PASSWORD))
                            .withCourse(new Course.Builder()
                                    .withId(courseService.getCourseId(req.getParameter(COURSE)))
                                    .withName(req.getParameter(COURSE).toUpperCase())
                                    .build())
                            .withAssessment(Integer.valueOf(req.getParameter(ASSESSMENT).isEmpty() ? "0" : req.getParameter(ASSESSMENT)))
                            .withReview(req.getParameter(REVIEW).toUpperCase())
                            .build()
            );
        } catch (SQLException | UserNotFoundException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(String.format(FORMAT_ADMIN_SERVLET_PATH, req.getContextPath()));
    }
}