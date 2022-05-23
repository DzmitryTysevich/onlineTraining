package com.epam.finaltask.onlinetraining.dao;

import com.epam.finaltask.onlinetraining.constants.SqlQuery;
import com.epam.finaltask.onlinetraining.domain.Course;
import com.epam.finaltask.onlinetraining.extractor.CourseResultSetExtractor;
import com.epam.finaltask.onlinetraining.jdbctemplate.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    private final JdbcTemplate<Course> jdbcTemplate;

    public CourseDAOImpl(JdbcTemplate<Course> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Course> findCourseByName(String name) throws SQLException {
        return jdbcTemplate.query(SqlQuery.COURSE_BY_NAME, new CourseResultSetExtractor(), name);
    }

    @Override
    public void createCourse(String name) throws SQLException {
        jdbcTemplate.update(SqlQuery.ADD_COURSE, name);
    }
}