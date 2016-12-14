package com.example.asiagibson.redoeasteregg.Rv;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.asiagibson.redoeasteregg.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by asiagibson on 12/13/16.
 */

public class EeAdapter extends RecyclerView.Adapter {

    private List<Integer> mTurtleList = Arrays.asList(
            R.drawable.landturtle,
            R.drawable.lifeturtle,
            R.drawable.purple,
            R.drawable.thisturtle,
            R.drawable.turtle,
            R.drawable.landturtle,
            R.drawable.lifeturtle,
            R.drawable.purple,
            R.drawable.thisturtle,
            R.drawable.turtle,
            R.drawable.landturtle,
            R.drawable.lifeturtle,
            R.drawable.purple,
            R.drawable.thisturtle,
            R.drawable.turtle
    );

    @Override
    public EeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EeViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EeViewHolder turtleHolder = (EeViewHolder) holder;
        Integer holderTurtle = mTurtleList.get(position);
        turtleHolder.bind(holderTurtle);

    }

    @Override
    public int getItemCount() {
        return mTurtleList.size();
    }
}
