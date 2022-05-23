package com.epam.finaltask.onlinetraining.domain;

import java.util.Objects;

public class User {
    private Long id;
    private Role role;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Course course;
    private Integer assessment;
    private String review;

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Course getCourse() {
        return course;
    }

    public Integer getAssessment() {
        return assessment;
    }

    public String getReview() {
        return review;
    }

    public static class Builder {
        private final User user;

        public Builder() {
            user = new User();
        }

        public Builder withId(Long id) {
            user.id = id;
            return this;
        }

        public Builder withRole(Role role) {
            user.role = role;
            return this;
        }

        public Builder withFirstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder withCourse(Course course) {
            user.course = course;
            return this;
        }

        public Builder withAssessment(Integer assessment) {
            user.assessment = assessment;
            return this;
        }

        public Builder withReview(String review) {
            user.review = review;
            return this;
        }

        public User build() {
            return user;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(role, user.role) && Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", course=" + course +
                ", assessment=" + assessment +
                ", review='" + review + '\'' +
                '}';
    }
}