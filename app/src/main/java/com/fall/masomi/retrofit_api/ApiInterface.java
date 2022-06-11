package com.fall.masomi.retrofit_api;


import com.fall.masomi.retrofit_api.GetFallResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;


public interface ApiInterface {

    @Multipart
    @GET("hafez/faal")
    Call<GetFallResponse> getFall();
}
