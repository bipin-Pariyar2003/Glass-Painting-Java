package com.mycompany.project.dao;

import com.mycompany.project.entities.Order;
import com.mycompany.project.helper.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDao {
    public void saveOrder(Order order) {
        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(order);
        tx.commit();
        session.close();
    }

    public List<Order> getAllOrders() {
        Session session = FactoryProvider.getFactory().openSession();
        List<Order> orders = session.createQuery("from Order", Order.class).list();
        session.close();
        return orders;
    }
}
