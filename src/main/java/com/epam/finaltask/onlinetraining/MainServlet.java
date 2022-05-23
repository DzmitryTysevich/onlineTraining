package com.epam.finaltask.onlinetraining;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static com.epam.finaltask.onlinetraining.constants.MapperConstant.GUEST;
import static com.epam.finaltask.onlinetraining.constants.MapperConstant.USER_NAME;
import static com.epam.finaltask.onlinetraining.constants.PathConstants.FORMAT_MAIN_PAGE;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        String userName = (String) httpSession.getAttribute(USER_NAME);
        if (Objects.isNull(userName)) {
            httpSession.setAttribute(USER_NAME, GUEST);
        }
        resp.sendRedirect(String.format(FORMAT_MAIN_PAGE, req.getContextPath()));
    }
}