package persistence.impl;


import persistence.admin.Admin;
import persistence.admin.AdminFactory;

public class AdminFactoryImpl implements AdminFactory {

    private static AdminFactory instance;

    private AdminFactoryImpl() {
    }

    @Override
    public Admin newAdmin(String username, String password, String role, String longitude, String latitude) {
        return new AdminImpl(username, password, role, longitude, latitude);
    }

    public static AdminFactory getInstance() {
        if (instance == null) {
            instance = new AdminFactoryImpl();
        }
        return instance;
    }

    private static class AdminImpl implements Admin {

        private final String username;
        private final String password;
        private final String role;
        private final String longitude;
        private final String latitude;

        public AdminImpl(String username, String password, String role, String longitude, String latitude) {
            this.username = username;
            this.password = password;
            this.role = role;
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        @Override
        public String toString() {
            return "AdminImpl{" + "username=" + username + ", password=" + password + ", role=" + role + ", longitude=" + longitude + ", latitude=" + latitude + '}';
        }

    }
}
