
package com.mycompany.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.mycompany.project.dao.UserDao;
import com.mycompany.project.entities.User;
import com.mycompany.project.helper.FactoryProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          //coding area

            //fetching the data from login.jsp//
           String email= request.getParameter("email");
           String password= request.getParameter("password");

           //validations


            //User authentication checking the database for user
            UserDao userDao= new UserDao(FactoryProvider.getFactory());
            User user=userDao.getUserByEmailAndPassword(email, password);

           HttpSession httpSession= request.getSession();

            if(user==null){

                httpSession.setAttribute("message", "Invalid Details !! Try with another one.");
                response.sendRedirect("login.jsp");
                return;
            }
            else{
                out.println("<h1>Welcome "+user.getUserName()+"</h1>");

                //login
                httpSession.setAttribute("current-user", user);


                if(user.getUserType().equals("admin")){
                    response.sendRedirect("admin.jsp");
                }
                else if (user.getUserType().equals("normal")) {
                    response.sendRedirect("index.jsp");
                }
                else{
                    out.println("<h1> we have not Identified User</h1>");
                }
                //admin

                //normal
            }


        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
