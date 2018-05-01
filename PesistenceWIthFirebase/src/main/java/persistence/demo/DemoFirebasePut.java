/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.demo;

import java.io.IOException;
import java.util.Date;
import firebase.persistence.IFirebasePersistence;
import file.persistence.Persistence;
import persistence.admin.Admin;
import persistence.admin.AdminFactory;
import persistence.impl.AdminFactoryImpl;

import persistence.implemations.FirebasePersistence;

/**
 *
 * @author MoK
 */
public class DemoFirebasePut {

    public static void main(String[] args) throws IOException {
//adding user Mo Kayed into firebase by userid4.
        AdminFactory factory = AdminFactoryImpl.getInstance();
        Admin mo = factory.newAdmin("Mo", "123","admin","0","0");

        IFirebasePersistence firebasestorage = new FirebasePersistence(mo);

        firebasestorage.putInFirebase(586);

    }

}
