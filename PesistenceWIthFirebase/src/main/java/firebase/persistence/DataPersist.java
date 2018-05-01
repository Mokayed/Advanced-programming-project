package firebase.persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.firebase.database.DatabaseReference;
import java.io.Closeable;

/**
 *
 * @author MoK
 */

    public interface DataPersist extends DatabaseReference.CompletionListener, Closeable{}

