package com.model.pojo;
// Generated Oct 26, 2020 11:10:05 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer id;
     private String username;
     private String password;
     private Set<Diary> diaries = new HashSet<Diary>(0);
     private Set<Diarylike> diarylikes = new HashSet<Diarylike>(0);

    public User() {
    }

	
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username, String password, Set<Diary> diaries, Set<Diarylike> diarylikes) {
       this.username = username;
       this.password = password;
       this.diaries = diaries;
       this.diarylikes = diarylikes;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Diary> getDiaries() {
        return this.diaries;
    }
    
    public void setDiaries(Set<Diary> diaries) {
        this.diaries = diaries;
    }
    public Set<Diarylike> getDiarylikes() {
        return this.diarylikes;
    }
    
    public void setDiarylikes(Set<Diarylike> diarylikes) {
        this.diarylikes = diarylikes;
    }




}


