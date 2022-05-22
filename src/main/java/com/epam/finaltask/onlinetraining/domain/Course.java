package com.epam.finaltask.onlinetraining.domain;

import java.util.Objects;

public class Course {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private final Course course;

        public Builder() {
            course = new Course();
        }

        public Builder withId(Integer id) {
            course.id = id;
            return this;
        }

        public Builder withName(String name) {
            course.name = name;
            return this;
        }

        public Course build() {
            return course;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}