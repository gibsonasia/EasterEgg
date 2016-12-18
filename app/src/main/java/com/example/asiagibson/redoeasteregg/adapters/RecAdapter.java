package com.example.asiagibson.redoeasteregg.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asiagibson.redoeasteregg.R;
import com.example.asiagibson.redoeasteregg.TurtleDatabase;
import com.example.asiagibson.redoeasteregg.Turtle;

import java.util.ArrayList;

import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class RecAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context mContenxt;
    private LayoutInflater mInflater;
    public ArrayList<String> arrName = new ArrayList<>();
    public ArrayList<byte[]> arrImage = new ArrayList<>();


    //constructor
    public RecAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContenxt = context;
        arrName = nameValue();
        arrImage = imageValue();

    }

    private ArrayList<byte[]> imageValue() {
        ArrayList<byte[]> valImage = new ArrayList<>();

        TurtleDatabase turtleDatabase = new TurtleDatabase(mContenxt);
        SQLiteDatabase db = turtleDatabase.getReadableDatabase();


        Cursor turtles = cupboard().withDatabase(db).query(Turtle.class).getCursor();
        try {

            QueryResultIterable<Turtle> itr = cupboard().withCursor(turtles).iterate(Turtle.class);

            for (Turtle turtle : itr) {
                valImage.add(turtle.image);
            }
        } finally {

            turtles.close();
        }
        return valImage;
    }

    public ArrayList<String> nameValue() {

        ArrayList<String> val = new ArrayList<>();

        TurtleDatabase turtleDatabase = new TurtleDatabase(mContenxt);
        SQLiteDatabase db = turtleDatabase.getReadableDatabase();


        Cursor turtles = cupboard().withDatabase(db).query(Turtle.class).getCursor();
        try {

            QueryResultIterable<Turtle> itr = cupboard().withCursor(turtles).iterate(Turtle.class);

            for (Turtle turtle : itr) {
                val.add(turtle.name + "");
            }
        } finally {

            turtles.close();
        }
        return val;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewOfOneRow = mInflater.inflate(R.layout.single_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(viewOfOneRow);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.mTvNum.setText(arrName.get(position));


        Bitmap bmp = BitmapFactory.decodeByteArray(arrImage.get(position), 0, arrImage.get(position).length);

        viewHolder.mImgTurtle.setImageBitmap(bmp);

    }

    @Override
    public int getItemCount() {
        return arrName.size();
    }
}
