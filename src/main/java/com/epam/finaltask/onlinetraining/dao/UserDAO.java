package com.epam.finaltask.onlinetraining.dao;

import com.epam.finaltask.onlinetraining.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> findAllUser() throws SQLException;

    List<User> findUsersByCourse(Integer id) throws SQLException;

    List<User> findUserByID(Long id) throws SQLException;

    List<User> findUserByEmail(String email) throws SQLException;

    boolean isUserExistByEmailAndPassword(String email, String password) throws SQLException;

    boolean isUserExistByEmail(String email) throws SQLException;

    boolean isUserExistById(Long id) throws SQLException;

    void deleteUser(Long id) throws SQLException;

    void createUser(Object... args) throws SQLException;

    void updateByAdmin(Object... args) throws SQLException;

    void updatePersonalData(Object... args) throws SQLException;

    void updateByTeacher(Object... args) throws SQLException;
}