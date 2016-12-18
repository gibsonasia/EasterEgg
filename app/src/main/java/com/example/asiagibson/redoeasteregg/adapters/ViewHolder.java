package com.example.asiagibson.redoeasteregg.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asiagibson.redoeasteregg.R;



public class ViewHolder extends RecyclerView.ViewHolder {

    TextView mTvNum;
    ImageView mImgTurtle;

    public ViewHolder(View itemView) {
        super(itemView);

        mTvNum = (TextView)itemView.findViewById(R.id.tv_num);
        mImgTurtle = (ImageView)itemView.findViewById(R.id.imgTurtle);

    }
}
