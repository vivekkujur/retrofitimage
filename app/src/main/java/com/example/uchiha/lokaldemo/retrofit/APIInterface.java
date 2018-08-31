package com.example.uchiha.lokaldemo.retrofit;

import com.example.uchiha.lokaldemo.modelpojo.MultipleResources;
import com.example.uchiha.lokaldemo.modelpojo.Recycler_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/list")
    Call<List<Recycler_model>> getlist();



}
