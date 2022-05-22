package com.epam.finaltask.onlinetraining.jdbctemplate;

import com.epam.finaltask.onlinetraining.extractor.ResultSetExtractor;

import java.sql.SQLException;
import java.util.List;

public interface JdbcTemplate<T> {
    List<T> query(String query, ResultSetExtractor<T> resultSetExtractor, Object... args) throws SQLException;

    void delete(String query, long id) throws SQLException;

    void update(String query, Object... args) throws SQLException;
}