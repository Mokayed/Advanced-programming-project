/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firebase.persistence;

import persistence.admin.Admin;

/**
 *
 * @author MoK
 */
public interface IFirebasePersistence {

    public void initFirebase();// call the firebase

    public void putInFirebase(long id);//add data to the firebase

    public String getFromFirebase(long id);// get data from firebase with id
}
