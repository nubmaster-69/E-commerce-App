package com.hisu.hisumal.model;

import java.io.Serializable;

public class User implements Serializable {

    private String fullName;
    private String email;
    private String userName;
    private String password;
    private String address;
    
    public User(String fullName, String email, String userName, String password, String address) {
        this.fullName = fullName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}