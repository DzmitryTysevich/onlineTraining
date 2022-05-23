package com.epam.finaltask.onlinetraining.dao;

import com.epam.finaltask.onlinetraining.domain.User;
import com.epam.finaltask.onlinetraining.extractor.UserResultSetExtractor;
import com.epam.finaltask.onlinetraining.jdbctemplate.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

import static com.epam.finaltask.onlinetraining.constants.SqlQuery.*;

public class UserDAOImpl implements UserDAO {
    private final JdbcTemplate<User> jdbcTemplate;

    public UserDAOImpl(JdbcTemplate<User> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAllUser() throws SQLException {
        return jdbcTemplate.query(ALL_USERS, new UserResultSetExtractor());
    }

    @Override
    public List<User> findUsersByCourse(Integer id) throws SQLException {
        return jdbcTemplate.query(STUDENTS_BY_COURSE, new UserResultSetExtractor(), id);
    }

    @Override
    public List<User> findUserByID(Long id) throws SQLException {
        return jdbcTemplate.query(USER_BY_ID, new UserResultSetExtractor(), id);
    }

    @Override
    public List<User> findUserByEmail(String email) throws SQLException {
        return jdbcTemplate.query(USER_BY_EMAIL, new UserResultSetExtractor(), email);
    }

    @Override
    public boolean isUserExistByEmailAndPassword(String email, String password) throws SQLException {
        return !jdbcTemplate.query(USER_BY_EMAIL_AND_PASSWORD, new UserResultSetExtractor(), email, password).isEmpty();
    }

    @Override
    public boolean isUserExistByEmail(String email) throws SQLException {
        return !jdbcTemplate.query(USER_BY_EMAIL, new UserResultSetExtractor(), email).isEmpty();
    }

    @Override
    public boolean isUserExistById(Long id) throws SQLException {
        return !jdbcTemplate.query(USER_BY_ID, new UserResultSetExtractor(), id).isEmpty();
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        jdbcTemplate.delete(DELETE_BY_ID, id);
    }

    @Override
    public void createUser(Object... args) throws SQLException {
        jdbcTemplate.update(ADD_USER, args);
    }

    @Override
    public void updateByAdmin(Object... args) throws SQLException {
        jdbcTemplate.update(UPDATE_BY_ADMIN, args);
    }

    @Override
    public void updatePersonalData(Object... args) throws SQLException {
        jdbcTemplate.update(UPDATE_PERSONAL_DATA, args);
    }

    @Override
    public void updateByTeacher(Object... args) throws SQLException {
        jdbcTemplate.update(UPDATE_BY_TEACHER, args);
    }
}