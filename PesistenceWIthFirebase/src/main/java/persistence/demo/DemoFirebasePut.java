/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.demo;

import firebase.persistence.IFirebaseConection;
import java.io.IOException;
import java.util.Date;
import firebase.persistence.IFirebasePersistence;
import persistence.examples.info.AdminInformation;
import persistence.implemations.FirebaseConectionImp;


import persistence.implemations.FirebasePersistence;

/**
 *
 * @author MoK
 */
public class DemoFirebasePut {

    public static void main(String[] args) throws IOException {
String link = "https://finaleapp-dcad7.firebaseio.com";
String path = "C:\\\\Users\\\\MoK\\\\Documents\\\\NetBeansProjects\\\\Firebase\\\\finaleapp-dcad7-firebase-adminsdk-ultqu-62bc411e68.json";
       
       IFirebaseConection IFC = new FirebaseConectionImp();
      IFC.initFirebase(link,path);
        
      
      
      AdminInformation mo = new AdminInformation("helsingør 3000", 0, 0, "mo123", "admin", "mo", "cool mo");
    IFirebasePersistence firebasestorage = new FirebasePersistence();
        firebasestorage.putInFirebase("hm3",mo);

    }

}
