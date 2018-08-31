package com.example.uchiha.lokaldemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.uchiha.lokaldemo.modelpojo.Recycler_model;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class Recycler_Adapter extends RecyclerView.Adapter <Recycler_holder> {


    private final List<Recycler_model> dataSet;

    private final Context context;
    private String name;

    public Recycler_Adapter(List<Recycler_model> dataSet, Context context) {
        this.context=context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public Recycler_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.imagecard,parent,false);

        Recycler_holder recycler_holder =new Recycler_holder(view);

        return recycler_holder;

    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_holder holder, final int position) {



      holder.filename.setText(dataSet.get(position).getFilename());

      holder.button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(context,"download ",Toast.LENGTH_LONG).show();
                name=dataSet.get(position).getFilename();
              String url= dataSet.get(position).getPostUrl();
              String download_url=url+"/download";
              String name= UUID.randomUUID().toString()+"'jpg";
              Picasso.with(context)
                      .load(download_url)
                      .into(target);
          }

      });



    }

    private Target target=new Target() {
        @Override
        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
            Log.d(TAG, "onBitmapLoaded: ");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File sd= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File folder= new File (sd,"/Picsum");

                    if(!folder.mkdir()){
                        Log.e("ERROR", "Cannot create a directory!");
                    }else{
                        Log.e("run:"," directory create" );
                        folder.mkdir();
                    }

                    File filename= new File (folder,name);

                   /* if(!filename.exists()){
                    try{
                        filename.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    }*/

                        try {
                            FileOutputStream outputStream =
                                    new FileOutputStream(String.valueOf(filename));

                            bitmap.compress(Bitmap.CompressFormat.JPEG,40,outputStream);
                            
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }



                }
            }).start();

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };


    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

}
