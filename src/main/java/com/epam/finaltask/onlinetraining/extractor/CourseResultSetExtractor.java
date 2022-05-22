package com.epam.finaltask.onlinetraining.extractor;

import com.epam.finaltask.onlinetraining.domain.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.*;

public class CourseResultSetExtractor implements ResultSetExtractor<Course> {
    @Override
    public List<Course> extractData(ResultSet resultSet) throws SQLException {
        List<Course> courses = new ArrayList<>();
        while (resultSet.next()) {
            courses.add(new Course.Builder()
                    .withId(resultSet.getInt(ID))
                    .withName(resultSet.getString(NAME).toUpperCase())
                    .build());
        }
        return courses;
    }
}