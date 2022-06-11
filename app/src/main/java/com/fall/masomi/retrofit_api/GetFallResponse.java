package com.fall.masomi.retrofit_api;

import com.google.gson.annotations.SerializedName;

public class GetFallResponse {
    @SerializedName("error")
    public boolean error;
    @SerializedName("error_msg")
    public String error_msg = "خطایی رخ داده است";

}
