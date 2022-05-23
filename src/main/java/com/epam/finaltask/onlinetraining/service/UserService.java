package com.epam.finaltask.onlinetraining.service;

import com.epam.finaltask.onlinetraining.dao.UserDAO;
import com.epam.finaltask.onlinetraining.dao.UserDAOImpl;
import com.epam.finaltask.onlinetraining.domain.User;
import com.epam.finaltask.onlinetraining.exceptions.UserHasBeenFoundException;
import com.epam.finaltask.onlinetraining.exceptions.UserNotFoundException;
import com.epam.finaltask.onlinetraining.jdbctemplate.JdbcTemplateImpl;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAOImpl(new JdbcTemplateImpl<>());
    }

    public List<User> getAllUser() throws SQLException {
        return userDAO.findAllUser();
    }

    public List<User> getUsersByCourse(Integer id) throws SQLException {
        return userDAO.findUsersByCourse(id);
    }

    public User getUserById(Long id) throws SQLException {
        List<User> user = userDAO.findUserByID(id);
        return user.isEmpty() ? null : user.get(0);
    }

    public User getUserByEmail(String email) {
        List<User> user;
        try {
            user = userDAO.findUserByEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user.isEmpty() ? null : user.get(0);
    }

    public void deleteUser(Long id) throws SQLException, UserNotFoundException {
        if (userDAO.isUserExistById(id)) {
            userDAO.deleteUser(id);
        } else {
            throw new UserNotFoundException(String.format("User with id: %d doesn't exist", id));
        }
    }

    public void registerUser(User user) throws SQLException, UserHasBeenFoundException {
        if (userDAO.isUserExistByEmail(user.getEmail())) {
            throw new UserHasBeenFoundException(String.format("User with email: %s has been found earlier", user.getEmail()));
        } else {
            userDAO.createUser(
                    user.getRole().name(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword()
            );
        }
    }

    public void updateByAdmin(User user) throws SQLException, UserNotFoundException {
        if (userDAO.isUserExistByEmail(user.getEmail())) {
            userDAO.updateByAdmin(
                    user.getRole().name(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getCourse().getId(),
                    user.getAssessment(),
                    user.getReview(),
                    user.getId()
            );
        } else {
            throw new UserNotFoundException(String.format("User with email: %s doesn't exist", user.getEmail()));
        }
    }

    public void updatePersonalData(User user) throws SQLException, UserNotFoundException {
        if (userDAO.isUserExistByEmail(user.getEmail())) {
            userDAO.updatePersonalData(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getCourse().getId(),
                    user.getId()
            );
        } else {
            throw new UserNotFoundException(String.format("User with email: %s doesn't exist", user.getEmail()));
        }
    }

    public void updateByTeacher(User user) throws SQLException, UserNotFoundException {
        if (userDAO.isUserExistByEmail(user.getEmail())) {
            userDAO.updateByTeacher(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getCourse().getId(),
                    user.getAssessment(),
                    user.getReview(),
                    user.getId()
            );
        } else {
            throw new UserNotFoundException(String.format("User with email: %s doesn't exist", user.getEmail()));
        }
    }

    public boolean isUserAuth(String email, String password) {
        try {
            return userDAO.isUserExistByEmailAndPassword(email, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}