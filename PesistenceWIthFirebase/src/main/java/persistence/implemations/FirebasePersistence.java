/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.implemations;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;
import firebase.persistence.IFirebasePersistence;
import persistence.admin.Admin;

/**
 *
 * @author MoK
 */
public class FirebasePersistence implements IFirebasePersistence {

    private Admin admin;

    public FirebasePersistence(Admin admins) {

        admin = admins;
    }

    public FirebaseDatabase firebaseDatabase;

    @Override
    public void initFirebase() {
        try {

            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                    .setDatabaseUrl("https://finaleapp-dcad7.firebaseio.com")
                    .setServiceAccount(new FileInputStream(new File("C:\\Users\\MoK\\Documents\\NetBeansProjects\\Firebase\\finaleapp-dcad7-firebase-adminsdk-ultqu-62bc411e68.json")))
                    .build();

            FirebaseApp.initializeApp(firebaseOptions);
            firebaseDatabase = FirebaseDatabase.getInstance();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void putInFirebase(long id) {
        String name = "user" + id;
        if (id != 0) {

            initFirebase();
            CountDownLatch countDownLatch = new CountDownLatch(1);

            DatabaseReference databaseReference = firebaseDatabase.getReference("/");

            DatabaseReference childReference = databaseReference.child("users").child(name);

            childReference.setValue(admin, new DatabaseReference.CompletionListener() {

                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                    System.out.println("Record saved!");
                    // decrement countDownLatch value and application will be continues its execution.
                    countDownLatch.countDown();
                }
            });
            try {
                //wait for firebase to saves record.
                countDownLatch.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public String getFromFirebase(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

   /*private static class DataFirebasePersist implements DataPersist {

        @Override
        public void onComplete(DatabaseError de, DatabaseReference dr) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            System.out.println("Record saved!");

            countDownLatch.countDown();

            try {
                //wait for firebase to saves record.
                countDownLatch.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void close() throws IOException {
            out.close();
        }
    }
}*/
