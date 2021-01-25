/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.pojo.Diary;
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author HP-PC
 */
public class StoriesDAO {

    public void createStory(Diary diary) {
        Session session
                = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.merge(diary);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        session.close();
    }

    public List<Diary> fetchPublicDiary() {
        List<Diary> diaries = new ArrayList<>();
        Session session
                = HibernateUtil.getSessionFactory().openSession();

        try {

            session.beginTransaction();
            Query qu = session.createQuery("FROM Diary");
            diaries = qu.list();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        
        return diaries;
    }

    public List<Diary> fetchPersonalDiary(int currentUserId) {
        List<Diary> diaries = new ArrayList<>();
        Session session
                = HibernateUtil.getSessionFactory().openSession();

        try {

            session.beginTransaction();
            Query qu = session.createQuery("FROM Diary D Where D.user.id=:id");
            qu.setParameter("id", currentUserId);
            diaries = qu.list();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        
        return diaries;
    }

    public List<Diary> getDiaryById(int diaryId) {
        Session session
                = HibernateUtil.getSessionFactory().openSession();

        Diary diary = new Diary();

        List<Diary> diaries = new ArrayList<>();

        try {
            session.beginTransaction();
            Query qu = session.createQuery("From Diary D where D.id=:id");
            qu.setParameter("id", diaryId);
            diary = (Diary) qu.uniqueResult();
            diaries.add(diary);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        
        session.close();

        return diaries;
    }

    public void delete(Diary diary) {
        Session session
                = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("delete Diary D where D.id =:id");
            q.setParameter("id", diary.getId());
            q.executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    public void updateDiary(Diary diary) {
        Session session
                = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.update(diary);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        session.close();
    }

}
