package com.example.asiagibson.redoeasteregg.Rv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asiagibson.redoeasteregg.R;

/**
 * Created by asiagibson on 12/13/16.
 */

public class EeViewHolder extends RecyclerView.ViewHolder {
    private final View mView;
    EditText etextView;
    ImageView imageView;



    public EeViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        mView = itemView;
        imageView = (ImageView)mView.findViewById(R.id.iv_turtle);
        etextView = (EditText) mView.findViewById(R.id.et_name_change);
    }

    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.ee_cardview, parent, false);
    }

    public void bind(Integer holderTurtle) {
//        mView.set(holderTurtle);
        imageView.setImageResource(holderTurtle);
        etextView.getText();

    }
}
