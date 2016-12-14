package com.example.asiagibson.redoeasteregg.Rv;

/**
 * Created by asiagibson on 12/13/16.
 */

public class EasterEggView {
    private String changeName;
    private Integer turtlePic;

    public EasterEggView(String changeName, Integer pic){
        this.changeName = changeName;
        this.turtlePic = pic;
    }

    public String getChangeName(){
        return changeName;
    }

    public Integer getTurtlePic(){
        return turtlePic;
    }
}
