package com.example.contacts;

import android.media.Image;

import java.io.Serializable;

public class Contact implements Serializable {
    int Id;
    String name;
    String phone;
    Image avatar;

    public String getName() {

        return name;
    }

    public String getPhone() {

        return phone;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public void setAvatar(Image avatar) {

        this.avatar = avatar;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
