package com.example.asiagibson.redoeasteregg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Nesada on 12/11/2016.
 */
public class TurtleDatabase extends SQLiteOpenHelper{

    public TurtleDatabase(Context context) {
        super(context, "myData3.db", null, 1);
    }

    static {
        cupboard().register(Turtle.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        cupboard().withDatabase(db).upgradeTables();
    }
}
