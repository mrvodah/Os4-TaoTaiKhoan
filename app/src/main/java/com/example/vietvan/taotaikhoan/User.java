package com.example.vietvan.taotaikhoan;

import java.io.Serializable;

/**
 * Created by VietVan on 09/06/2018.
 */

public class User implements Serializable{

    public int id;
    public String username, passtouch, password;

    public User(int id, String username, String password, String passtouch) {
        this.id = id;
        this.username = username;
        this.passtouch = passtouch;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasstouch() {
        return passtouch;
    }

    public void setPasstouch(String passtouch) {
        this.passtouch = passtouch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passtouch='" + passtouch + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
