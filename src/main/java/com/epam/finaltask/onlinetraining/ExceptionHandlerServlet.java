package com.epam.finaltask.onlinetraining;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.finaltask.onlinetraining.constants.PathConstants.ERROR_PAGE;

@WebServlet(name = "ExceptionHandler", urlPatterns = "/exceptionHandler")
public class ExceptionHandlerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("exception", request.getAttribute("javax.servlet.error.exception"));
        session.setAttribute("statusCode", request.getAttribute("javax.servlet.error.status_code"));
        session.setAttribute("servletName", request.getAttribute("javax.servlet.error.servlet_name"));
        session.setAttribute("requestUri", request.getAttribute("javax.servlet.error.request_uri"));

        session.getServletContext().getRequestDispatcher(ERROR_PAGE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}