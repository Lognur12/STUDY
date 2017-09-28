package ru.javacourse.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 15.07.2017.
 */
@javax.servlet.annotation.WebServlet(name = "SecondServlet", urlPatterns = "/second")
public class SecondServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

//        Object name = request.getSession().getAttribute("NAME");
//        response.getWriter().write("Hello " + name + " from Second Servlet!!!");
        Object attr = (String)request.getAttribute("SUPER-NAME");
        System.out.println(attr);

        getServletContext().getRequestDispatcher("/name.jsp").forward(request, response);
    }
}
