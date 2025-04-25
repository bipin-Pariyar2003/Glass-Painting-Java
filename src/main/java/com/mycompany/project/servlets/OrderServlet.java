package com.mycompany.project.servlets;

import com.mycompany.project.dao.OrderDao;
import com.mycompany.project.entities.Order;
import com.mycompany.project.entities.Product;
import com.mycompany.project.entities.User;
import com.mycompany.project.helper.FactoryProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current-user");

        if (user == null) {
            session.setAttribute("message", "You are not logged in!! Login first to place an order");
            response.sendRedirect("login.jsp");
            return;
        }

        String address = request.getParameter("address");
        // Create and save the order
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setUserId(user.getUserID());
        order.setAddress(address);
        // Assuming you have a method to calculate total amount
        float totalAmount = 100; // Placeholder value, you should calculate it based on the products
        order.setTotalAmount(totalAmount);

        // Add products to the order (this is just a placeholder, adapt as necessary)
        List<Product> products = new ArrayList<>(); // Fetch products based on your cart logic
        order.setProducts(products);

        // Save the order
        OrderDao orderDao = new OrderDao();
        orderDao.saveOrder(order);

        session.setAttribute("message", "Order placed successfully!");
        response.sendRedirect("confirmation.jsp");
    }
}
