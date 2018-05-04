/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firebase.persistence;




/**
 *
 * @author MoK
 */
public interface IFirebasePersistence {

    public void initFirebase();// call the firebase

    public void putInFirebase(String username);//add data to the firebase

    public void getFromFirebase(String username);// get data from firebase with id
}
