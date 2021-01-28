/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.LikesDAO;
import com.model.pojo.Diary;
import com.model.pojo.Diarylike;
import com.model.pojo.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@ManagedBean
@RequestScoped
public class LikesBean {
    
    private LikesDAO likesDAO = new LikesDAO();

    /**
     * Creates a new instance of LikesBean
     */
    public LikesBean() {
    }

    public void like(Diary diary) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        
        int currentUserId = Integer.parseInt(session.getAttribute("user_session_id").toString());
        
        for (Diarylike diarylike : diary.getDiarylikes()) {
            if(diarylike.getUser().getId() == currentUserId) {
                return;
            }
        }

        User userCreating = new User();
        userCreating.setId(currentUserId);

        Diarylike diarylike = new Diarylike();
        diarylike.setDiary(diary);
        diarylike.setUser(userCreating);
        
        likesDAO.like(diarylike);
    }

}
