/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Test;

import com.controller.ControllerDao;
import com.domain.DeadPersonal;
import com.domain.LoginInfo;

/**
 *
 * @author ngabo
 */
public class Test {

    public static void main(String[] args) {
        DeadPersonal dp = new  DeadPersonal();
        
        dp.setGender("male");
        new ControllerDao().kubika(dp);
            
    }
}
