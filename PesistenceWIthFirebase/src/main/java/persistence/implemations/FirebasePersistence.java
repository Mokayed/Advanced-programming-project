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
import persistence.admin.Admin;
import persistence.admin.AdminFactory;
import persistence.impl.AdminFactoryImpl;

/**
 *
 * @author MoK
 */
public class FirebasePersistence implements IFirebasePersistence {

    private Admin admin;

    public FirebasePersistence(Admin admins) {

        admin = admins;
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
            CountDownLatch countDownLatch = new CountDownLatch(1);

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
    public String getFromFirebase(String username) {
        initFirebase();
       
        List<Admin> universityList = new ArrayList<>();
        DatabaseReference databaseReference = firebaseDatabase.getReference("/");
        DatabaseReference childReference = databaseReference.child("users").child("hm1");
        childReference.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {      System.out.println("testssss");
                for (DataSnapshot postSnapshot : ds.getChildren()) {
                   
                    
                    
                    Admin university = postSnapshot.getValue(Admin.class);
                    AdminFactory factory = AdminFactoryImpl.getInstance();
              
                    
                    
                    universityList.add(factory.newAdmin( 
                            postSnapshot.child("username").getValue().toString(),
                            postSnapshot.child("latitude").getValue().toString(),
                            postSnapshot.child("longitude").getValue().toString(),
                            postSnapshot.child("role").getValue().toString(),
                            postSnapshot.child("title").getValue().toString()
               
                    
                    
                    )); 
                    // here you can access to name property like university.name
                }
                System.out.println(universityList.toString()+"gdegg"); 
            }
    

            @Override
            public void onCancelled(DatabaseError de) {
              System.out.println("The read failed: " + de.getMessage());

            }
        });
String cool = "cool";
        return cool;

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
