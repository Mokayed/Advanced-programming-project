/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.demo;

import firebase.persistence.IFirebasePersistence;
import java.io.IOException;
import persistence.implemations.FirebasePersistence;

/**
 *
 * @author MoK
 */
public class DemoGet {

    public static void main(String[] args) throws IOException {
        IFirebasePersistence firebase = new FirebasePersistence();
        firebase.getFromFirebase("hm3");

    }
}
