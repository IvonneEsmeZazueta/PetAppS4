package com.example.petapp.domain;

import com.google.gson.annotations.Expose;

public class Pets {
    @Expose private String name;
    @Expose private String img;
    @Expose private int bones;

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public int getBones() {
        return bones;
    }
}
