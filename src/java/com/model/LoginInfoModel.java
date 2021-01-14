/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.ControllerDao;
import com.domain.LoginInfo;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ngabo
 */
@ManagedBean(name = "lm")
@SessionScoped
public class LoginInfoModel {
    private LoginInfo info = new LoginInfo();
    
    private String username;
    private String password;

    public LoginInfo getInfo() {
        return info;
    }

    public void setInfo(LoginInfo info) {
        this.info = info;
    }

    
    
    

    /**
     * Creates a new instance of LoginInfoModel
     */
    public LoginInfoModel() {
    }
    
    public String addUser(){
        try {
            ControllerDao cd = new ControllerDao();
            cd.kubika(info);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("New manager has been added"));
            return "loginPages.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("fail" +e));
            return "index.xhtml?faces-redirect=true";
        }
        
    }
    public String login(){
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            
            ControllerDao cd = new ControllerDao();
            LoginInfo ui = cd.retrieveUserInfo(username, password);
            System.out.println(username);
            fc.getExternalContext().getSessionMap().put("userinfo", ui);
            
            return "/admin/undertaker.xhtml?faces-redirect=true";
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("username or password are not registered"));
            return "loginPages.xhtml?faces-redirect=true";
        }
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String logoutt(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/loginPages.xhtml?faces-redirect=true";
    }
    
}
