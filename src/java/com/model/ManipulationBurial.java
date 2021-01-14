/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.ControllerDao;
import com.domain.DeadPersonal;
import com.domain.Undertaker;
import com.utilities.MailHandler;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ngabo
 */
@ManagedBean(name = "man")
@SessionScoped
public class ManipulationBurial {

    private Undertaker undertaker = new Undertaker();
    private DeadPersonal deadPersonal = new DeadPersonal();
    private boolean update = false;
    private List<DeadPersonal> deadPersonals;
    private String pname;
    

    public Undertaker getUndertaker() {
        return undertaker;
    }

    public void setUndertaker(Undertaker undertaker) {
        this.undertaker = undertaker;
    }

    public DeadPersonal getDeadPersonal() {
        return deadPersonal;
    }

    public void setDeadPersonal(DeadPersonal deadPersonal) {
        this.deadPersonal = deadPersonal;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public ManipulationBurial() {
        this.deadPersonals = new ControllerDao().allPersons();

    }

    public String registration() {
        try {
            ControllerDao cd = new ControllerDao();
            cd.kubika(deadPersonal);
            MailHandler.sendMail(deadPersonal.getUndertaker().getEmail(), "GUSHIGURA STATUS", "you registered the deadperson(" + deadPersonal.getPersonName() + ") and his undertaker(" + deadPersonal.getUndertaker().getName() + ")");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Information saved successfully"));
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fail to save information " + e));
        }
        return "deadPersonInfo.xhtml?faces-redirect=true";
    }

    public String updateUndertaker() {
        try {
            ControllerDao cd = new ControllerDao();
            cd.guhindura(deadPersonal);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("data has been updated"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("data has been failed to be updated" + e));
        }
        return "deadPersonInfo.xhtml?faces-redirect=true";
    }

    public String findAll() {
        ControllerDao cd = new ControllerDao();
        deadPersonals = new ArrayList<>();
        for (DeadPersonal d : cd.allPersons()) {
            if (d.getPersonName().equals(pname.trim())) {
                deadPersonals.add(d);
            }
        }
        if (pname.trim().equals("") || pname.trim() == null) {
            deadPersonals = new ControllerDao().allPersons();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("list"));
        return "deadPersonInfo.xhtml?faces-redirect=true";
    }

    public String updateDeadP(DeadPersonal dp) {
        deadPersonal = dp;
        return "/admin/undertaker.xhtml?faces-redirect=true";
    }

    public String deleteDeadp(DeadPersonal person) {
        try {
            ControllerDao cd = new ControllerDao();
            cd.gusiba(person);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("data has been deleted"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("failed to be deleted"));
        }
        deadPersonals = new ControllerDao().allPersons();
        return "deadPersonInfo.xhtml?faces-redirect=true";
    }

    public List<DeadPersonal> getDeadPersonals() {
        return deadPersonals;
    }

    public void setDeadPersonals(List<DeadPersonal> deadPersonals) {
        this.deadPersonals = deadPersonals;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    
}
