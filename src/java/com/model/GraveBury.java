/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.ControllerDao;
import com.domain.Cemetery;
import com.domain.DeadPersonal;
import com.domain.Grave;
import com.domain.Status;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ngabo
 */
@ManagedBean(name = "bury")
@SessionScoped
public class GraveBury {

    private Grave imva = new Grave();
    private DeadPersonal deadPerson = new DeadPersonal();
    private Cemetery cemetery = new Cemetery();
    private List<DeadPersonal> persons;
    private List<Cemetery> irimbis = new ArrayList<>();
    private boolean update = false;
    private Map<Integer, String> mapPerson;
    private Date burialDat;
    private List<Grave> graves;
    private int graveNo;

    /**
     * Creates a new instance of GraveBury
     */
    public GraveBury() {
        this.graves = new ControllerDao().allGraves();
        mapPerson = new LinkedHashMap<>();
        for (DeadPersonal p : new ControllerDao().allPersons()) {
            if (p.getStatus() == Status.PENDING) {
                mapPerson.put(p.getPersonID(), p.getPersonName());
            }

        }
    }

    

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public Grave getImva() {
        return imva;
    }

    public void setImva(Grave imva) {
        this.imva = imva;
    }

    public DeadPersonal getDeadPerson() {
        return deadPerson;
    }

    public void setDeadPerson(DeadPersonal deadPerson) {
        this.deadPerson = deadPerson;
    }

    public Cemetery getCemetery() {
        return cemetery;
    }

    public void setCemetery(Cemetery cemetery) {
        this.cemetery = cemetery;
    }

    public List<DeadPersonal> getPersons() {
        return persons;
    }

    public void setPersons(List<DeadPersonal> persons) {
        this.persons = persons;
    }

    public List<Cemetery> getIrimbis() {
        return irimbis;
    }

    public void setIrimbis(List<Cemetery> irimbis) {
        this.irimbis = irimbis;
    }

    public Map<Integer, String> getMapPerson() {
        return mapPerson;
    }

    public void setMapPerson(Map<Integer, String> mapPerson) {
        this.mapPerson = mapPerson;
    }

    public String saveImva() {
        try {
            
            ControllerDao cd = new ControllerDao();
            imva.setDeadPerson(deadPerson);
            imva.setCemetery(cemetery);
            cd.kubika(imva);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Information saved successfully"));
            DeadPersonal p = new ControllerDao().readPerson(deadPerson.getPersonID());
            p.setStatus(Status.BURIED);
            new ControllerDao().guhindura(p);
            graves = new ControllerDao().allGraves();
            return "displayGrave.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("fail to save " +e));
            return "gravee.xhtml?faces-redirect=true";
        }
    }

    public String findll() {
        ControllerDao cd = new ControllerDao();
        graves = new ArrayList<>();
        for (Grave g : cd.allGraves()) {
            if (g.getImvaNo() == graveNo) {
                graves.add(g);
            }
            
            
        }
        
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("List of Grave"));
        return "displayGrave.xhtml?faces-redirect=true";
    }

    public String updateGrave() {
        try {
            ControllerDao cd = new ControllerDao();
            imva.setDeadPerson(deadPerson);
            imva.setCemetery(cemetery);
            imva.setBurialDate(imva.getBurialDate());
            cd.guhindura(imva);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("grave has been updated"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("failed to update"));
        }
        return "displayGrave.xhtml?faces-redirect=true";

    }
    public String updateGr(Grave grave) {
        imva = grave;
        return "/admin/gravee.xhtml?faces-redirect=true";
    }
    public String deleteGr(Grave g){
        try {
            ControllerDao cd = new ControllerDao();
            cd.gusiba(g);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("data has been deleted"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("failed to be deleted"));
        }
        return "displayGrave.xhtml?faces-redirect=true";
    }

    public List<Grave> getGraves() {
        return graves;
    }

    public void setGraves(List<Grave> graves) {
        this.graves = graves;
    }

    public int getGraveNo() {
        return graveNo;
    }

    public void setGraveNo(int graveNo) {
        this.graveNo = graveNo;
    }
    

}
