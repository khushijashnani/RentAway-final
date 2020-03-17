package com.project.rentaway;

import java.util.ArrayList;

public class User {
    private String id;
    private String email;
    private String userName;
    private String password;
    private String phone;
    private ArrayList<Property> propertyArrayList;
    private ArrayList<String> favs;
    public User(){

    }

    public User(String id, String email, String userName, String password, String phone, ArrayList<Property> propertyArrayList, ArrayList<String> favs) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.propertyArrayList = propertyArrayList;
        this.favs = favs;
    }

    public ArrayList<String> getFavs() {
        return favs;
    }

    public void setFavs(ArrayList<String> favs) {
        this.favs = favs;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProperty(ArrayList<Property> property) {
        this.propertyArrayList = property;
    }

    public ArrayList<Property> getProperty() {
        return propertyArrayList;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
