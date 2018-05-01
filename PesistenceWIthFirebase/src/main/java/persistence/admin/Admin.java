/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.admin;

import java.util.Date;

/**
 *
 * @author MoK
 */
public interface Admin {
    
    public String getUsername();

    public String getPassword();

    public String getRole();

    public String getLongitude();

    public String getLatitude();
}
