package com.example.asiagibson.redoeasteregg;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.asiagibson.redoeasteregg.Rv.EeAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TurtleDatabaseHelper dbHelper = TurtleDatabaseHelper.getInstance(this);
        db = dbHelper.getWritableDatabase();

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
        mRecyclerView.setAdapter(new EeAdapter());

    }
}
