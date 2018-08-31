package com.example.uchiha.lokaldemo;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;

public class SaveImageHelper implements Target {
    private  Context context;
    private WeakReference<ContentResolver> contentResolverWeakReference;
    private  String filename;
    private  String author;

    public SaveImageHelper(Context context, ContentResolver contentResolverWeakReference, String filename, String author) {
        this.context=context;
        this.contentResolverWeakReference = new WeakReference<ContentResolver>(contentResolverWeakReference);
        this.filename = filename;
        this.author = author;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

        ContentResolver contentResolver =contentResolverWeakReference.get();
        if(contentResolver!=null){

            MediaStore.Images.Media.insertImage(contentResolver,bitmap,filename,author);
            Toast.makeText(context,"download success",Toast.LENGTH_LONG).show();

        }
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        context.startActivity(Intent.createChooser(intent,"VIEW PICTURE"));

    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        Toast.makeText(context,"download failed",Toast.LENGTH_LONG).show();

    }


    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}
