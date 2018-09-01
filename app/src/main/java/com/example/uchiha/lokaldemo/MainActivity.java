package com.example.uchiha.lokaldemo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uchiha.lokaldemo.modelpojo.Recycler_model;
import com.example.uchiha.lokaldemo.retrofit.APIClient;
import com.example.uchiha.lokaldemo.retrofit.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    private ProgressDialog progressDialog;

    private static final String WRITE_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String READ_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1234;

    private boolean mWritePermissionGranted = false;

    APIInterface apiInterface;
    TextView textView;
    RecyclerView recyclerView;
    Recycler_Adapter adapter;
    private APIClient client;

    private Call<List<Recycler_model>> listCall1;
    private Call<List<Recycler_model>> listdownload;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog=new ProgressDialog(this);
        getLocationPermission();
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    private void download() {
       // listCall1= apiInterface.get

    }

    private void showjson() {

        progressDialog.setMessage("Loading...");
        progressDialog.show();
        apiInterface = APIClient.getClient().create(APIInterface.class);

        listCall1=apiInterface.getlist();
        listCall1.enqueue(new Callback<List<Recycler_model>>() {
       @Override
       public void onResponse(Call<List<Recycler_model>> call, Response<List<Recycler_model>> response) {

           List<Recycler_model> data= response.body();
           adapter = new Recycler_Adapter(data,getApplicationContext());
           Toast.makeText(MainActivity.this,"sucess",Toast.LENGTH_LONG).show();

           progressDialog.dismiss();
           recyclerView.setAdapter(adapter);


       }

       @Override
       public void onFailure(Call<List<Recycler_model>> call, Throwable t) {
        Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_LONG).show();
       }
    });

    }

    private void getLocationPermission(){

        Log.d(TAG, "getwrite permission: request for permissions");
        String[] permission={WRITE_STORAGE,READ_STORAGE};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                WRITE_STORAGE)== PackageManager.PERMISSION_GRANTED){

            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    READ_STORAGE)== PackageManager.PERMISSION_GRANTED){

                mWritePermissionGranted=true;
                showjson();

            }else{
                ActivityCompat.requestPermissions(this,
                        permission,
                        STORAGE_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permission,
                    STORAGE_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){

            case STORAGE_PERMISSION_REQUEST_CODE:{

                if (grantResults.length > 0){
                    for (int i=0; i< grantResults.length;i++){

                        if( grantResults[i]!=PackageManager.PERMISSION_GRANTED){
                            mWritePermissionGranted=false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }

                    mWritePermissionGranted=true;
                    //initialize our map
                    showjson();
                }
            }

        }
    }


}
