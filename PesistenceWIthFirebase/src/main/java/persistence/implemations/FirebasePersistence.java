/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.implemations;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;
import firebase.persistence.IFirebasePersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MoK
 */
public class FirebasePersistence implements IFirebasePersistence {

    private AdminInformation admin;
    ArrayList<AdminInformation> adminlist = new ArrayList<>();

    public FirebasePersistence(AdminInformation admininfo) {

        admin = admininfo;
    }

    public FirebasePersistence() {
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
    public void putInFirebase(String username) {
        if (username != null) {

            initFirebase();
            CountDownLatch countDownLatch = new CountDownLatch(1);// thread call

            DatabaseReference databaseReference = firebaseDatabase.getReference("/");

            DatabaseReference childReference = databaseReference.child("users").child(username);

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
    public void getFromFirebase(String username) {
        initFirebase();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference childReference = database.getReference("users").child(username);
        CountDownLatch countDownLatch = new CountDownLatch(1);//thread call
        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {

                
                AdminInformation adminInfo = ds.getValue(AdminInformation.class);

                System.out.println(adminInfo.toString());
                countDownLatch.countDown();

            }

            @Override
            public void onCancelled(DatabaseError de) {
                System.out.println("The read failed: " + de.getCode());

            }
        });

        try {
              countDownLatch.await();

        } catch (InterruptedException ex) {
            Logger.getLogger(FirebasePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
