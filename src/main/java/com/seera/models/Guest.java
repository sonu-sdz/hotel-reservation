package com.seera.models;

public class Guest {

    private String type;
    private int age;


    public Guest(String type, int age) {
        this.type = type;
        this.age = age;
    }

    public Guest(String type) {
        this.type = type;
    }
}
