package com.epam.finaltask.onlinetraining.servlets.teacherServlet;

import com.epam.finaltask.onlinetraining.domain.Course;
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

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.*;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_TEACHER_SERVLET_PATH;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.TEACHER_UPDATE_PAGE;

@WebServlet("/access/teacherView/update/users/*")
public class UpdatableTeacherServlet extends HttpServlet {
    private final UserService userService;
    private final CourseService courseService;

    public UpdatableTeacherServlet() {
        userService = new UserService();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        try {
            Long id = Long.parseLong(req.getPathInfo().substring(1));
            User userById = userService.getUserById(id);
            session.setAttribute(USER_BY_ID, userById);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.getServletContext().getRequestDispatcher(TEACHER_UPDATE_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            userService.updateByTeacher(
                    new User.Builder()
                            .withId(Long.valueOf(req.getParameter(ID)))
                            .withFirstName(req.getParameter(FIRST_NAME).toUpperCase())
                            .withLastName(req.getParameter(LAST_NAME).toUpperCase())
                            .withEmail(req.getParameter(EMAIL).toUpperCase())
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
        resp.sendRedirect(String.format(FORMAT_TEACHER_SERVLET_PATH, req.getContextPath()));
    }
}