package com.example.chianne.foodhack;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author mcw0805
 */
public class User {

    private String email;
    private String name;
    private long datetime;
    private String address;
    private String uid;



    public User(String email, String name, long datetime, String address, String uid) {
        this.email = email;
        this.name = name;
        this.datetime = datetime;
        this.address = address;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public static void postToDB() {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");

    }
}
