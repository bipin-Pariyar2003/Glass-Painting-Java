package com.mycompany.project.servlets;

import java.io.*;

import com.mycompany.project.dao.CategoryDao;
import com.mycompany.project.dao.ProductDao;
import com.mycompany.project.entities.Category;
import com.mycompany.project.entities.Product;
import com.mycompany.project.helper.FactoryProvider;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "ProductOperationServlet", urlPatterns = { "/ProductOperationServlet" })
public class ProductOperationServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            // checking if it is category or product
            String op = request.getParameter("operation");

            if (op.trim().equals("addCategory")) {
                // fetching category data
                String title = request.getParameter("catTitle");
                String description = request.getParameter("catDescription");
                Category category = new Category();
                category.setCategoryTitle(title);
                category.setCategoryDescription(description);

                // save category in database//
                CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());
                int catId = categoryDao.saveCategory(category);
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("message", "category added successfully :" + catId);
                response.sendRedirect("admin.jsp");
                return;
            }

            else if (op.trim().equals("addProduct")) {
                // fetching product data
                String productName = request.getParameter("productName");
                String productDescription = request.getParameter("productDescription");
                int productPrice = Integer.parseInt(request.getParameter("productPrice"));
                int productDiscount = Integer.parseInt(request.getParameter("productDiscount"));
                int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
                int catId = Integer.parseInt(request.getParameter("catId"));
                Part part = request.getPart("pPic");

                Product p = new Product();
                p.setPName(productName);
                p.setPDesc(productDescription);
                p.setPPrice(productPrice);
                p.setPDiscount(productDiscount);
                p.setPQuantity(productQuantity);
                p.setPPhoto(part.getSubmittedFileName());

                // get category by ID:
                CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
                Category category = cdao.getCategoryById(catId);
                p.setCategory(category);

                // Saving the product
                ProductDao pdao = new ProductDao(FactoryProvider.getFactory());
                pdao.saveProduct(p);


                // upload picture
                // find out the path to upload photo
                String path = getServletContext().getRealPath("img")+ File.separator+"products"+File.separator+part.getSubmittedFileName();
                System.out.println(path);

                //uploading code:
                try{
                    FileOutputStream fos= new FileOutputStream(path);
                    InputStream is= part.getInputStream();

                    //reading data
                    byte []data= new byte[is.available()];
                    is.read(data);


                    //writing the data
                    fos.write(data);
                    fos.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("message", "Product added successfully ");
                response.sendRedirect("admin.jsp");
                return;

            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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


}
