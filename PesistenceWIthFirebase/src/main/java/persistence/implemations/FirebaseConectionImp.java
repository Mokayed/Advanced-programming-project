/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.implemations;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import firebase.persistence.IFirebaseConection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author MoK
 */

//making a connection to the needed firebase using Ifirebaseconnection interface.
public class FirebaseConectionImp implements IFirebaseConection{
    public FirebaseDatabase firebaseDatabase;
    @Override
 public void initFirebase(String link, String path) {
        try {

            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                    .setDatabaseUrl(link)
                    .setServiceAccount(new FileInputStream(new File(path)))
                    .build();

            FirebaseApp.initializeApp(firebaseOptions);
            firebaseDatabase = FirebaseDatabase.getInstance();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
}
