/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.StoriesDAO;
import com.model.pojo.Diary;
import com.model.pojo.User;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP-PC
 */
@ManagedBean
@RequestScoped
public class StoriesBean {

    private Diary diary = new Diary();
    private StoriesDAO storiesDAO = new StoriesDAO();
    private int diaryParam = 0;

    /**
     * Creates a new instance of StoriesBean
     */
    public StoriesBean() {
    }

    public void onLoad() {
        // checking if user is logged-in using session

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        if (session.getAttribute("user_session_id") == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void createStory() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        User userCreating = new User();
        userCreating.setId(Integer.parseInt(session.getAttribute("user_session_id").toString()));

        diary.setUser(userCreating);
        diary.setDate(new Date(System.currentTimeMillis()));

        storiesDAO.createStory(diary);
        try {
            facesContext.getExternalContext().redirect("./mystories.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(StoriesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStory(Diary diary) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        User userCreating = new User();
        userCreating.setId(Integer.parseInt(session.getAttribute("user_session_id").toString()));

        diary.setUser(userCreating);
        diary.setDate(new Date(System.currentTimeMillis()));
        storiesDAO.updateDiary(diary);
        try {
            facesContext.getExternalContext().redirect("./mystories.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(StoriesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Diary> fetchPublicDiaries() {
        return storiesDAO.fetchPublicDiary();
    }

    public List<Diary> fetchPersonalDiaries() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        int currentUserId = Integer.parseInt(session.getAttribute("user_session_id").toString());

        return storiesDAO.fetchPersonalDiary(currentUserId);
    }

    public void deleteDiary(Diary diary) {
        storiesDAO.delete(diary);
    }

    public List<Diary> getDiayById(int diaryId) {
        this.diaryParam = diaryId;
        List<Diary> diaries = storiesDAO.getDiaryById(diaryParam);

        this.diary = diaries.get(0);

        return diaries;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

}
