package com.epam.finaltask.onlinetraining.service;

import com.epam.finaltask.onlinetraining.dao.CourseDAO;
import com.epam.finaltask.onlinetraining.dao.CourseDAOImpl;
import com.epam.finaltask.onlinetraining.domain.Course;
import com.epam.finaltask.onlinetraining.jdbctemplate.JdbcTemplateImpl;

import java.sql.SQLException;
import java.util.List;

public class CourseService {
    private final CourseDAO courseDAO;

    public CourseService() {
        courseDAO = new CourseDAOImpl(new JdbcTemplateImpl<>());
    }

    public Course getCourseByName(String name) throws SQLException {
        List<Course> course = courseDAO.findCourseByName(name);
        return course.isEmpty() ? null : course.get(0);
    }

    public void createCourse(String name) throws SQLException {
        courseDAO.createCourse(name);
    }

    public Integer getCourseId(String name) throws SQLException {
        if (getCourseByName(name) != null) {
            return getCourseByName(name).getId();
        } else {
            try {
                createCourse(name);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Integer courseId = null;
            try {
                courseId = getCourseByName(name).getId();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return courseId;
        }
    }
}