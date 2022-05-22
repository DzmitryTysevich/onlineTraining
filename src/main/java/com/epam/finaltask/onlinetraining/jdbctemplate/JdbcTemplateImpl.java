package com.epam.finaltask.onlinetraining.jdbctemplate;

import com.epam.finaltask.onlinetraining.connection.DBCPDataSource;
import com.epam.finaltask.onlinetraining.extractor.ResultSetExtractor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplateImpl<T> implements JdbcTemplate<T> {
    private Connection connection;

    {
        try {
            this.connection = DBCPDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> query(String query, ResultSetExtractor<T> resultSetExtractor, Object... args) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        for (int argIndex = 0, paramIndex = 1; argIndex < args.length; argIndex++, paramIndex++) {
            preparedStatement.setObject(paramIndex, args[argIndex]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetExtractor.extractData(resultSet);
    }

    @Override
    public void delete(String query, long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(String query, Object... args) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int argIndex = 0, paramIndex = 1; argIndex < args.length; argIndex++, paramIndex++) {
            preparedStatement.setObject(paramIndex, args[argIndex]);
        }
        preparedStatement.executeUpdate();
    }
}