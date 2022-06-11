package com.fall.masomi.retrofit_api;


import com.fall.masomi.Poet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("hafez/faal")
    Call<GetFallResponse> getFall();

    @GET("poets")
    Call<List<Poet>> getPoets();

}
