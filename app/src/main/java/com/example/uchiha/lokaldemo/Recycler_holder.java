package com.example.uchiha.lokaldemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class Recycler_holder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView filename;
    public Button button;

    public Recycler_holder(View itemView) {

        super(itemView);

        this.filename = (TextView) itemView.findViewById(R.id.filename);
        this.button=itemView.findViewById(R.id.download);

    }

    @Override
    public void onClick(View v) {

    }
}
