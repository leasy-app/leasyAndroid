package com.leasy.leasyAndroid.api;

import com.google.gson.JsonObject;
import com.leasy.leasyAndroid.model.Category;
import com.leasy.leasyAndroid.model.CourseModel;
import com.leasy.leasyAndroid.model.PostsListItem;
import com.leasy.leasyAndroid.model.ReadPostItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("polls/Categories")
    Call<List<Category>> getAllCategories();

    @GET("polls/Posts/")
    Call<List<PostsListItem.PostItem>> getAllPosts(@Query("categorie") String category, @Query("writer") String writer);

    @GET("polls/Content/")
    Call<List<List<ReadPostItem>>> getPostContent(@Query("id") String id);

    @GET("polls/AddFullPost")
    Call<Object> addFullPost(
            @Query("name") String title,
            @Query("photo") String photoUrl,
            @Query("categorie") String category,
            @Query("writer") String writer,
            @Query("content1") String content1,
            @Query("content2") String content2,
            @Query("main_content") String mainContent,
            @Query("summary") String summary
    );

    @GET("polls/GetCourse")
    Call<List<CourseModel>> getCourse(@Query("course") String courseId);

    @GET("polls/AddCourse")
    Call<Object> addCourse(@Query("name") String title,
                           @Query("picture") String picture,
                           @Query("explanation") String description,
                           @Query("posts") String posts);

    @GET("polls/GetPostCourse")
    Call<List<PostsListItem.PostItem>> getCoursePosts(@Query("course") String courseId);
}
