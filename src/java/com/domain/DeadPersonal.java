/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ngabo
 */
@Entity
public class DeadPersonal implements Serializable {
    @Id
    @GeneratedValue
    private int PersonID;
    @Column(unique = true)
    private String IdCard;
    private String personName;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dod;
    private Undertaker undertaker = new Undertaker();
    @OneToOne(mappedBy = "deadPerson")
    private Grave grave;
    @Enumerated(EnumType.STRING)
    private Status status=Status.PENDING;
    private String gender;
    public DeadPersonal() {
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int PersonID) {
        this.PersonID = PersonID;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String IdCard) {
        this.IdCard = IdCard;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDod() {
        return dod;
    }

    public void setDod(Date dod) {
        this.dod = dod;
    }

    public Undertaker getUndertaker() {
        return undertaker;
    }

    public void setUndertaker(Undertaker undertaker) {
        this.undertaker = undertaker;
    }

    public Grave getGrave() {
        return grave;
    }

    public void setGrave(Grave grave) {
        this.grave = grave;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

   
    
    
}
