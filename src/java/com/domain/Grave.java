/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ngabo
 */
@Entity
public class Grave implements Serializable{
    @Id
    @GeneratedValue
    private int imvaNo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date burialDate;
    @ManyToOne
    private Cemetery cemetery = new Cemetery();
    @OneToOne
    private DeadPersonal deadPerson = new DeadPersonal();

    public Grave() {
    }

    public int getImvaNo() {
        return imvaNo;
    }

    public void setImvaNo(int imvaNo) {
        this.imvaNo = imvaNo;
    }

    public Date getBurialDate() {
        return burialDate;
    }

    public void setBurialDate(Date burialDate) {
        this.burialDate = burialDate;
    }

    public Cemetery getCemetery() {
        return cemetery;
    }

    public void setCemetery(Cemetery cemetery) {
        this.cemetery = cemetery;
    }

    public DeadPersonal getDeadPerson() {
        return deadPerson;
    }

    public void setDeadPerson(DeadPersonal deadPerson) {
        this.deadPerson = deadPerson;
    }
    
    
}
