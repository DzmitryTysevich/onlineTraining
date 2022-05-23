package com.epam.finaltask.onlinetraining.servlets.studentServlet;

import com.epam.finaltask.onlinetraining.domain.Course;
import com.epam.finaltask.onlinetraining.domain.User;
import com.epam.finaltask.onlinetraining.exceptions.UserNotFoundException;
import com.epam.finaltask.onlinetraining.service.CourseService;
import com.epam.finaltask.onlinetraining.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.*;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_STUDENT_SERVLET_PATH;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_STUDENT_UPDATE_PAGE;

@WebServlet("/access/studentView/update")
public class UpdatableStudentServlet extends HttpServlet {
    private final UserService userService;
    private final CourseService courseService;

    public UpdatableStudentServlet() {
        userService = new UserService();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute(AUTH_EMAIL);
        User userByEmail = userService.getUserByEmail(email);
        session.setAttribute(USER_BY_EMAIL, userByEmail);

        resp.sendRedirect(String.format(FORMAT_STUDENT_UPDATE_PAGE, req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            userService.updatePersonalData(
                    new User.Builder()
                            .withId(Long.valueOf(req.getParameter(ID)))
                            .withFirstName(req.getParameter(FIRST_NAME).toUpperCase())
                            .withLastName(req.getParameter(LAST_NAME).toUpperCase())
                            .withEmail(req.getParameter(EMAIL).toUpperCase())
                            .withPassword(req.getParameter(PASSWORD))
                            .withCourse(new Course.Builder()
                                    .withId(courseService.getCourseId(req.getParameter(COURSE)))
                                    .withName(req.getParameter(COURSE).toUpperCase())
                                    .build())
                            .build()
            );
        } catch (SQLException | UserNotFoundException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(String.format(FORMAT_STUDENT_SERVLET_PATH, req.getContextPath()));
    }
}