package com.epam.finaltask.onlinetraining.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.AUTH_EMAIL;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.AUTH_PASSWORD;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.AUTH_ROLE;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.USER_NAME;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_SLASH_SERVLET_PATH;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(AUTH_EMAIL);
        session.removeAttribute(AUTH_PASSWORD);
        session.removeAttribute(AUTH_ROLE);
        session.removeAttribute(USER_NAME);

        resp.sendRedirect(String.format(FORMAT_SLASH_SERVLET_PATH, req.getContextPath()));
    }
}