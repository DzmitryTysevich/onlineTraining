package com.epam.finaltask.onlinetraining.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultSetExtractor<T> {
    List<T> extractData(ResultSet resultSet) throws SQLException;
}