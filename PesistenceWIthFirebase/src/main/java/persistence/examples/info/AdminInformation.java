/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.examples.info;

/**
 *
 * @author MoK
 */
public class AdminInformation {

    private String address;
    private long latitude;
    private long longitude;
    private String password;
    private String role;
    private String username;
    private String title;

    public AdminInformation() {
    }

    public AdminInformation(String address, long latitude, long longitude, String password, String role, String username, String title) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.password = password;
        this.role = role;
        this.username = username;
        this.title = title;
    }

    public String getAddress() {
        return address;
    }



    public long getLatitude() {
        return latitude;
    }


    public long getLongitude() {
        return longitude;
    }



    public String getPassword() {
        return password;
    }

  

    public String getRole() {
        return role;
    }


    public String getUsername() {
        return username;
    }

    

    public String getTitle() {
        return title;
    }


    @Override
    public String toString() {
        return "AdminInformation{" + "address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + ", password=" + password + ", role=" + role + ", username=" + username + ", title=" + title + '}';
    }

}
