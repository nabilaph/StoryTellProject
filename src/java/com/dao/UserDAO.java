/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.pojo.User;
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author HP-PC
 */
public class UserDAO {

    public List<User> login(User user) {
        Session session
                = HibernateUtil.getSessionFactory().openSession();

        List<User> userList = new ArrayList<>();

        try {
            session.beginTransaction();
            Query qu = session.createQuery("From User U where U.username=:username AND U.password=:password");
            qu.setParameter("username", user.getUsername());
            qu.setParameter("password", user.getPassword());
            userList = qu.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return userList;
    }

    public void signUp(User newUser) {
        Session session
                = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.merge(newUser);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        session.close();
    }

}
