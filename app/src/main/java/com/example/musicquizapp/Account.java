package com.example.musicquizapp;

import java.io.Serializable;

public class Account implements Serializable {
    String SDT;
    String id;
    String pass;

    public Account(String SDT, String id, String pass) {
        this.SDT = SDT;
        this.id = id;
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public Account() {
    }

    public String getSDT() {
        return SDT;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return pass;
    }
}
