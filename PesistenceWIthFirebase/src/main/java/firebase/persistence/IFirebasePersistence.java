/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firebase.persistence;

import persistence.examples.info.AdminInformation;

/**
 *
 * @author MoK
 */
public interface IFirebasePersistence {

    public void putInFirebase(String username, AdminInformation admin);//add data to the firebase

    public AdminInformation getFromFirebase(String username);// get data from firebase with id
}
