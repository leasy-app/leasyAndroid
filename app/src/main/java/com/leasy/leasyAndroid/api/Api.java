package com.leasy.leasyAndroid.api;

import com.leasy.leasyAndroid.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("polls/Categories")
    Call<List<Category>> getAllCategories();
}
