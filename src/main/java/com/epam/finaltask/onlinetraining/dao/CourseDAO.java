package com.epam.finaltask.onlinetraining.dao;

import com.epam.finaltask.onlinetraining.domain.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    List<Course> findCourseByName(String name) throws SQLException;

    void createCourse(String name) throws SQLException;
}