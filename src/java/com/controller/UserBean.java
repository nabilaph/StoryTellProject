/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.UserDAO;
import com.model.pojo.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP-PC
 */
@ManagedBean
@RequestScoped
public class UserBean {

    private User user = new User();
    private UserDAO userDao = new UserDAO();

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    public void login() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        List<User> users = userDao.login(user);

        if (users.size() > 0) {
            try {
                // valid
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                session.setAttribute("user_session_id", users.get(0).getId().toString());
                facesContext.getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // invalid
            facesContext.addMessage("globalLogin", new FacesMessage("Wrong username or password!"));
        }

    }

    public void signup() {
        userDao.signUp(user);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            facesContext.getExternalContext().redirect("login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUser() {
        return user;
    }

}
