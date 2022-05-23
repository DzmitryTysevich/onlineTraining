package com.epam.finaltask.onlinetraining.mappers;

import com.epam.finaltask.onlinetraining.domain.Course;
import com.epam.finaltask.onlinetraining.domain.Role;
import com.epam.finaltask.onlinetraining.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.*;
import static java.sql.Types.NULL;

public class MapperComponent {
    private static MapperComponent mapperComponent;

    private MapperComponent() {
    }

    public static MapperComponent getInstance() {
        if (mapperComponent == null) {
            mapperComponent = new MapperComponent();
        }
        return mapperComponent;
    }

    public User getUser(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .withId(resultSet.getLong(ID))
                .withRole(Role.valueOf(resultSet.getString(ROLE).toUpperCase()))
                .withFirstName(resultSet.getString(FIRST_NAME).toUpperCase())
                .withLastName(resultSet.getString(LAST_NAME).toUpperCase())
                .withEmail(resultSet.getString(EMAIL).toUpperCase())
                .withPassword(resultSet.getString(PASSWORD))
                .withCourse(resultSet.getInt(COURSE) == NULL ? null : getCourse(resultSet))
                .withAssessment(resultSet.getInt(ASSESSMENT) == NULL ? null : resultSet.getInt(ASSESSMENT))
                .withReview(resultSet.getString(REVIEW))
                .build();
    }

    private Course getCourse(ResultSet resultSet) throws SQLException {
        return new Course.Builder()
                .withId(resultSet.getInt(COURSE))
                .withName(resultSet.getString(NAME).toUpperCase())
                .build();
    }
}