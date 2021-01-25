/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.pojo.Diary;
import com.model.pojo.Diarylike;
import com.model.pojo.User;
import com.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author HP-PC
 */
public class LikesDAO {

    public void like(Diarylike diaryLike) {
        Session session
                = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.merge(diaryLike);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        session.close();
    }

}
