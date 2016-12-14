package com.example.asiagibson.redoeasteregg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by asiagibson on 12/14/16.
 */

public class TurtleDatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "turtle.db";
    private static final int DATABASE_VERSION = 1;

    private static TurtleDatabaseHelper instance;

    public static synchronized TurtleDatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (instance == null) {
            instance = new TurtleDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }


    private TurtleDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
