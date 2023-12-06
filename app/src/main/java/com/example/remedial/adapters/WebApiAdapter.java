package com.example.remedial.adapters;


import com.example.remedial.services.WebApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebApiAdapter {
    private static WebApiService webApiService;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static WebApiService getWebApiService(){
        if(webApiService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            webApiService = retrofit.create(WebApiService.class);
        }
        return webApiService;
    }
}
