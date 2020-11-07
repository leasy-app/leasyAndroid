package com.leasy.leasyAndroid.api;

import com.leasy.leasyAndroid.model.Category;
import com.leasy.leasyAndroid.model.PostsListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("polls/Categories")
    Call<List<Category>> getAllCategories();

    @GET("polls/Posts/")
    Call<List<PostsListItem.PostItem>> getAllPosts(@Query("categorie") String category);


}
