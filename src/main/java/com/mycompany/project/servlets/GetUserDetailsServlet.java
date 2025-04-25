package com.mycompany.project.servlets;

import com.mycompany.project.entities.User;
import com.mycompany.project.dao.UserDao;
import com.mycompany.project.helper.FactoryProvider;
import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.*;

@WebServlet(name = "GetUserDetailsServlet", urlPatterns = {"/getUserDetailsServlet"})
public class GetUserDetailsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Fetch user details based on user ID
            int userId = Integer.parseInt(request.getParameter("userId"));
            UserDao userDao = new UserDao(FactoryProvider.getFactory());
            User user = userDao.getUserById(userId);

            // Display user details in HTML format
            out.println("<h5>User Name: " + user.getUserName() + "</h5>");
            out.println("<p>User Email: " + user.getUserEmail() + "</p>");
            // Add more user details here as needed
        }
    }
}
