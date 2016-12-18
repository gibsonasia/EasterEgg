package com.example.asiagibson.redoeasteregg;

/**
 * Created by Nesada on 12/12/2016.
 */
public class Turtle {

    public String name;
    public byte[] image;

    public Turtle() {
        this.name = "noName";
        this.image = null;
    }

    public Turtle(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }
}
