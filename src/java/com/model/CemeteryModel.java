/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.ControllerDao;
import com.domain.Cemetery;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ngabo
 */
@ManagedBean(name = "mbi")
@SessionScoped
public class CemeteryModel {
    private Cemetery cemetery = new Cemetery();
    private boolean  update=false;
    private Map<Integer, String> cemeteryMap;

    /**
     * Creates a new instance of CemeteryModel
     */
    public CemeteryModel() {
        cemeteryMap = new LinkedHashMap<>();
        for (Cemetery irimbi1 : new ControllerDao().findCemetery() ) {
            cemeteryMap.put(irimbi1.getIrimbiNo(), irimbi1.getName());
        }
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
    
    
    
    public Cemetery getCemetery() {
        return cemetery;
    }

    public void setCemetery(Cemetery cemetery) {
        this.cemetery = cemetery;
    }

    public Map<Integer, String> getCemeteryMap() {
        return cemeteryMap;
    }

    public void setCemeteryMap(Map<Integer, String> cemeteryMap) {
        this.cemeteryMap = cemeteryMap;
    }
    
    public String saveCemetery() {
        try {
            ControllerDao cd = new ControllerDao();
            cd.kubika(cemetery);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Information saved successfully"));
            return "cemeteryDisplay.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("fail to save "));
            return "undertaker.xhtml?faces-redirect=true";
        }
    }
    public List<Cemetery> displayCemetery(){
        ControllerDao cd = new ControllerDao();
        List<Cemetery> l = cd.findl();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("list of Cemetery"));
        return l;
    }
    public String updateCem(){
        try {
            ControllerDao cd = new ControllerDao();
            cd.guhindura(cemetery);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("update has been successfully"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("failed to update"));
        }
        return "cemeteryDisplay.xhtml?faces-redirect=true";
    }
    public String updateCe(Cemetery c){
        cemetery = c;
        return "/admin/cemetery.xhtml?faces-redirect=true";
    }
    public String deleteCemet(Cemetery c){
        try {
            ControllerDao cd = new ControllerDao();
            cd.gusiba(c);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("deleted successfull"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("delet failed"));
        }
        return "cemeteryDisplay.xhtml?faces-redirect=true";
    }
}
