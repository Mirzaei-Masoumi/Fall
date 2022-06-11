package com.fall.masomi.retrofit_api;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("hafez/faal")
    Call<GetFallResponse> getFall();
}
