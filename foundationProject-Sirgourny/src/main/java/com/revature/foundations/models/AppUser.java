package com.revature.foundations.models;

import java.util.Objects;

public class AppUser {

    // These are my fields, next make a constructor
    private String username;
    private String password;

    public AppUser(String username) {
        this.username = username;
        this.password = "default-password";
    }

    // Constructor
    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AppUser(int i, String briana, String mania, String s, String brianaMa, String briana00) {

    }

    // Getter
    public String getUsername() {
        return username;
    }
    // Setter
    public void setUsername(String username) {
        this.username = username;
    }
    // Getter
    public String getPassword() {
        return password;
    }
    // Setter
    public void setPassword(String password) {
        this.password = password;
    }

    /* the following two @Override were generated here by selecting 'equals() and hashcode()',
    then "Select all non-null fields' */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        String forTheDemo = ((AppUser) o).username;
        AppUser appUser = (AppUser) o;
        return Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    // toString()
    @Override
    public String toString() {
        return "AppUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
