/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.implemations;

import persistence.examples.info.AdminInformation;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import firebase.persistence.IFirebaseConection;
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

    CountDownLatch countDownLatch = new CountDownLatch(1);//thread call
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public FirebasePersistence() {

    }

// (implementation of IFirebasePersistnce interface)puting data in the firebase using the put frim the Ifirebasepersistence
    //+ the inner class CompletionListenerImp
    @Override
    public void putInFirebase(String username, AdminInformation admin) {
        if (username != null) {

            DatabaseReference databaseReference = database.getInstance().getReference("/");

            DatabaseReference childReference = databaseReference.child("users").child(username);
            CompletionListenerImpl Complistner = new CompletionListenerImpl();
            childReference.setValue(admin, Complistner);
            try {
                //wait for firebase to saves record.
                countDownLatch.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

//(implementation of IFirebasePersistnce interface) geting data from firebase using the get from the ifirebase interface 
//+ the inner class valuelistenermip
    @Override
    public AdminInformation getFromFirebase(String username) {

        DatabaseReference childReference = database.getReference("users").child(username);

        ValueEListnerImpl listener = new ValueEListnerImpl();
        childReference.addValueEventListener(listener);
        try {
            countDownLatch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebasePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }

        AdminInformation admin = listener.getAdmin();

        return admin;

    }

    private class ValueEListnerImpl implements ValueEventListener {

        private AdminInformation adminInfo;

        public ValueEListnerImpl() {
        }

        @Override
        public void onDataChange(DataSnapshot ds) {
            adminInfo = ds.getValue(AdminInformation.class);
            System.out.println(adminInfo.toString());
            countDownLatch.countDown();

        }

        @Override
        public void onCancelled(DatabaseError de) {
            System.out.println("The read failed: " + de.getCode());
        }

        private AdminInformation getAdmin() {
            return adminInfo;
        }

    }

    private class CompletionListenerImpl implements CompletionListener {

        public CompletionListenerImpl() {
        }

        @Override
        public void onComplete(DatabaseError de, DatabaseReference dr) {
            System.out.println("Record saved!");
            // decrement countDownLatch value and application will be continues its execution.
            countDownLatch.countDown();
        }
    }

}
