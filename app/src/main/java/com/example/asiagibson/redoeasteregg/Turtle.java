package com.example.asiagibson.redoeasteregg;

/**
 * Created by asiagibson on 12/14/16.
 */

public class Turtle {

    public Turtle(){
        this.name ="unknown";
    }

    private Long _id;

    private String name;

    public Long getId(){
        return _id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


}
