package com.mycompany.project.dao;

import com.mycompany.project.entities.User;
import com.mycompany.project.helper.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {
    private SessionFactory factory;

    public UserDao(SessionFactory factory) {
        this.factory = factory;
    }

    //get user by email and password
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        try {
            String query = "from User where userEmail=:e and userPassword=:p";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("e", email);
            q.setParameter("p", password);
            user = (User) q.uniqueResult();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    //new new new
    public User getUserById(int userId) {
        User user = null;
        try (Session session = FactoryProvider.getFactory().openSession()) {
            String queryString = "FROM User WHERE userID = :userId";
            Query<User> query = session.createQuery(queryString, User.class);
            query.setParameter("userId", userId);
            user = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    //new new new

//    public List<User> getAllUsers() {
//        List<User> users = null;
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            String queryString = "FROM User";
//            Query<User> query = session.createQuery(queryString, User.class);
//            users = query.getResultList();
//            tx.commit();
//        } catch (Exception e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        }
//        return users;
//    }
}
