package com.fall.masomi.retrofit_api;

import java.io.IOException;

public class NoInternetException extends IOException {

    @Override
    public String getMessage() {
        return "No Internet!!!";
    }

}