/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.implemations;

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

    public void setAddress(String address) {
        this.address = address;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "AdminInformation{" + "address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + ", password=" + password + ", role=" + role + ", username=" + username + ", title=" + title + '}';
    }

}
