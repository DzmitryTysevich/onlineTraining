package com.epam.finaltask.onlinetraining.filter;

import com.epam.finaltask.onlinetraining.domain.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.*;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.*;
import static java.util.Objects.nonNull;

@WebFilter("/access/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        String authEmail = (String) session.getAttribute(AUTH_EMAIL);
        String authPassword = (String) session.getAttribute(AUTH_PASSWORD);

        if (nonNull(authEmail) && nonNull(authPassword)) {
            Role role = (Role) session.getAttribute(AUTH_ROLE);
            moveToMenu(request, response, role);
        } else {
            response.sendRedirect(String.format(FORMAT_ERROR_HANDLER_PATH, request.getContextPath()));
        }
    }

    private void moveToMenu(HttpServletRequest request, HttpServletResponse response, Role role) throws IOException, ServletException {
        if (role.equals(Role.ADMIN)) {
            HttpSession session = request.getSession();
            String adminServletPath = ADMIN_SERVLET_PATH;
            String subAdminServletPath = getSubUserServletPath(request, adminServletPath);
            session.getServletContext().getRequestDispatcher(adminServletPath + subAdminServletPath).forward(request, response);
        } else if (role.equals(Role.STUDENT)) {
            HttpSession session = request.getSession();
            String studentServletPath = STUDENT_SERVLET_PATH;
            String subStudentServletPath = getSubUserServletPath(request, studentServletPath);
            session.getServletContext().getRequestDispatcher(studentServletPath + subStudentServletPath).forward(request, response);
        } else if (role.equals(Role.TEACHER)) {
            HttpSession session = request.getSession();
            String teacherServletPath = TEACHER_SERVLET_PATH;
            String subTeacherServletPath = getSubUserServletPath(request, teacherServletPath);
            session.getServletContext().getRequestDispatcher(teacherServletPath + subTeacherServletPath).forward(request, response);
        } else {
            response.sendRedirect(String.format(FORMAT_SLASH_SERVLET_PATH, request.getContextPath()));
        }
    }

    private String getSubUserServletPath(HttpServletRequest request, String userServletPath) {
        if (request.getRequestURI().contains(userServletPath)) {
            return request.getRequestURI().substring(getBeginIndex(request, userServletPath));
        } else {
            return null;
        }
    }

    private int getBeginIndex(HttpServletRequest request, String userServletPath) {
        return request.getContextPath().length() + userServletPath.length();
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}