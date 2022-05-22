package com.epam.finaltask.onlinetraining.constants;

public class SqlQuery {
    public static final String ALL_USERS =
            "SELECT * FROM users u " +
                    "LEFT JOIN course c ON u.course = c.id " +
                    "ORDER BY u.id ASC";

    public static final String STUDENTS_BY_COURSE =
            "SELECT * FROM users u " +
                    "LEFT JOIN course c ON u.course = c.id " +
                    "WHERE u.course = ? AND u.role = 'STUDENT' " +
                    "ORDER BY u.id ASC";

    public static final String USER_BY_ID =
            "SELECT * FROM users u " +
                    "LEFT JOIN course c ON u.course = c.id " +
                    "WHERE u.id = ?";

    public static final String USER_BY_EMAIL =
            "SELECT * FROM users u " +
                    "LEFT JOIN course c ON u.course = c.id " +
                    "WHERE u.email = ?";

    public static final String USER_BY_EMAIL_AND_PASSWORD =
            "SELECT * FROM users u " +
                    "LEFT JOIN course c ON u.course = c.id " +
                    "WHERE u.email = ? AND u.password = ?";

    public static final String DELETE_BY_ID =
            "DELETE FROM users WHERE id = ?";

    public static final String UPDATE_BY_ADMIN =
            "UPDATE users SET " +
                    "role = ?, firstname = ?, lastname = ?, email = ?, password = ?, course = ?, assessment = ?, review = ? " +
                    "WHERE id = ?";

    public static final String UPDATE_PERSONAL_DATA =
            "UPDATE users SET " +
                    "firstname = ?, lastname = ?, email = ?, password = ?, course = ? " +
                    "WHERE id = ?";

    public static final String UPDATE_BY_TEACHER =
            "UPDATE users SET " +
                    "firstname = ?, lastname = ?, email = ?, course = ?, assessment = ?, review = ? " +
                    "WHERE id = ?";

    public static final String ADD_USER =
            "INSERT INTO users (role, firstname, lastname, email, password) " +
                    "VALUES (?, ?, ?, ?, ?)";

    public static final String ADD_COURSE =
            "INSERT INTO course (name) " +
                    "VALUES (?)";

    public static final String COURSE_BY_NAME =
            "SELECT * FROM course WHERE name = ?";

    private SqlQuery() {
    }
}