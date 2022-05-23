package com.epam.finaltask.onlinetraining.extractor;

import com.epam.finaltask.onlinetraining.domain.User;
import com.epam.finaltask.onlinetraining.mappers.MapperComponent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserResultSetExtractor implements ResultSetExtractor<User> {

    @Override
    public List<User> extractData(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(MapperComponent.getInstance().getUser(resultSet));
        }
        return users;
    }
}