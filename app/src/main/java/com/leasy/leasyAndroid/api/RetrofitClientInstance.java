package com.leasy.leasyAndroid.api;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static final String API_BASE_URL = "https://calm-hamlet-80940.herokuapp.com/";

    public static Retrofit getRetrofitInstance() {
        Gson gson = new Gson();
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            retrofit = builder.client(httpClient.build()).build();
        }
        return retrofit;
    }
}