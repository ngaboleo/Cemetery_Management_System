/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.domain.Cemetery;
import com.domain.DeadPersonal;
import com.domain.Grave;
import com.domain.LoginInfo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ngabo
 */
public class ControllerDao {
    public<T> String kubika(T t){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(t);
        s.getTransaction().commit();
        s.close();
        return "saved successfully";
    }
    public<T> String guhindura(T t){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(t);
        s.getTransaction().commit();
        s.close();
        return "updated successfully";
    }
    public <T> String gusiba(T t){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.delete(t);
        s.getTransaction().commit();
        s.close();
        return "Deleted successfully";
    }
    public List<DeadPersonal> allPersons(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<DeadPersonal> people = s.createQuery("FROM DeadPersonal").list();
        s.close();
        return people;
    }
    public  List<Cemetery> findCemetery(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Cemetery> cemeterys = s.createQuery("FROM Cemetery").list();
        s.close();
        return cemeterys;
    }
    public DeadPersonal readPerson(int pId){
        Session s = HibernateUtil.getSessionFactory().openSession();
        DeadPersonal deadPerson = (DeadPersonal) s.get(DeadPersonal.class, pId);
        s.close();
        return deadPerson;
    }
    public List<Grave> allGraves(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Grave> l = s.createQuery("FROM Grave").list();
        s.close();
        return l;
    }
    public List<Cemetery> findl(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Cemetery> l = s.createQuery("FROM Cemetery").list();
        s.close();
        return l;
    }
    
    public LoginInfo retrieveUserInfo(String username, String password){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("From LoginInfo U where U.userName = :us and U.password = :p");
        q.setParameter("us", username);
        q.setParameter("p", password);
        LoginInfo loginInfo = (LoginInfo) q.uniqueResult();
        s.close();
        return  loginInfo;
    }
    public DeadPersonal retrieveDead(String name){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("From DeadPersonal D where D.name = :me");
        q.setParameter("me", name);
        DeadPersonal dp = (DeadPersonal) q.uniqueResult();
        s.close();
        return dp;
    } 
    public Grave readGrave(int gId){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Grave g = (Grave) s.get(Grave.class, gId);
        s.close();
        return g;
    }
}
