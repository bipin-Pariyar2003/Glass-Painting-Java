/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.project.entities.User;
import com.mycompany.project.helper.FactoryProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author bipin
 */
@WebServlet(name = "RegisterServlet", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            try {
                String userName = request.getParameter("username");
                String userEmail = request.getParameter("useremail");
                String userPassword = request.getParameter("userpassword");
                String userPhone = request.getParameter("userphone");
                String userAddress = request.getParameter("useraddress");

                //Validations
                if (userPassword.length() < 6) {
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("message", "Password must be at least 6 characters long.");
                    response.sendRedirect("register.jsp");
                    return;
                }
                // Phone number validation
                if (userPhone.length() != 10 || (!userPhone.startsWith("98") && !userPhone.startsWith("97"))) {
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("message", "Invalid phone number. It should start with 98 or 97 and be exactly 10 digits long.");
                    response.sendRedirect("register.jsp");
                    return;
                }
                try {
                    Long.parseLong(userPhone); // Ensure it's numeric
                } catch (NumberFormatException e) {
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("message", "Phone number must be numeric.");
                    response.sendRedirect("register.jsp");
                    return;
                }

                // Create user object to store data
                User user = new User(userName, userEmail, userPassword, userPhone, "default.jpg", userAddress, "normal");
                Session hibernateSession = FactoryProvider.getFactory().openSession();
                Transaction tx = hibernateSession.beginTransaction();

                int userId = (int) hibernateSession.save(user);

                tx.commit();
                hibernateSession.close();


                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("message", "Registration Successful !!!");
                response.sendRedirect("register.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}





