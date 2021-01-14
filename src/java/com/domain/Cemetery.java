/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ngabo
 */
@Entity
public class Cemetery implements Serializable{
    @Id
    @GeneratedValue
    private int irimbiNo;
    private String name;
    private String location;
    @OneToMany(mappedBy = "cemetery")
    private List<Grave> graves = new ArrayList<>();

    public Cemetery() {
    }

    public int getIrimbiNo() {
        return irimbiNo;
    }

    public void setIrimbiNo(int irimbiNo) {
        this.irimbiNo = irimbiNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Grave> getGraves() {
        return graves;
    }

    public void setGraves(List<Grave> graves) {
        this.graves = graves;
    }
    
    
}
