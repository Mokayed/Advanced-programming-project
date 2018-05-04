/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.demo;

import java.io.IOException;
import java.util.Date;
import firebase.persistence.IFirebasePersistence;
import persistence.implemations.AdminInformation;


import persistence.implemations.FirebasePersistence;

/**
 *
 * @author MoK
 */
public class DemoFirebasePut {

    public static void main(String[] args) throws IOException {

       
AdminInformation mo = new AdminInformation("helsingør 3000", 0, 0, "mo123", "admin", "mo", "cool mo");

    IFirebasePersistence firebasestorage = new FirebasePersistence(mo);
        firebasestorage.putInFirebase("hm3");

    }

}
