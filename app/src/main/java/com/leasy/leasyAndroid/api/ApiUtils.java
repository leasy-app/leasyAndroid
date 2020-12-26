package com.leasy.leasyAndroid.api;

import androidx.annotation.Nullable;

import com.google.gson.JsonObject;
import com.leasy.leasyAndroid.model.Category;
import com.leasy.leasyAndroid.model.CourseModel;
import com.leasy.leasyAndroid.model.PostsListItem;
import com.leasy.leasyAndroid.model.ReadPostItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiUtils {

    private final static String LOG_TAG = "API_UTILS_TAG";

    public static void requestGetAllCategories(UiCallBack uiCallBack, int code) {
        Call<List<Category>> call = RetrofitClientInstance.getRetrofitInstance()
                .create(Api.class).getAllCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    uiCallBack.onRequestSuccessful(response, code);
                } else if (500 <= response.code() && response.code() <= 599) {
                    uiCallBack.onInternalErrorFailure(code);
                } else if (response.code() != 401) {
                    uiCallBack.onRequestError(response, code);
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                uiCallBack.onRequestSendFailure(t, code);
            }
        });
    }

    public static void requestGetAllPosts(UiCallBack uiCallBack, int code, @Nullable String category, @Nullable String writer) {
        Call<List<PostsListItem.PostItem>> call = RetrofitClientInstance.getRetrofitInstance()
                .create(Api.class).getAllPosts(category, writer);
        call.enqueue(new Callback<List<PostsListItem.PostItem>>() {
            @Override
            public void onResponse(Call<List<PostsListItem.PostItem>> call, Response<List<PostsListItem.PostItem>> response) {
                if (response.isSuccessful()) {
                    uiCallBack.onRequestSuccessful(response, code);
                } else if (500 <= response.code() && response.code() <= 599) {
                    uiCallBack.onInternalErrorFailure(code);
                } else if (response.code() != 401) {
                    uiCallBack.onRequestError(response, code);
                }
            }

            @Override
            public void onFailure(Call<List<PostsListItem.PostItem>> call, Throwable t) {
                uiCallBack.onRequestSendFailure(t, code);
            }
        });
    }

    public static void requestGetPostContent(UiCallBack uiCallBack, int code, String id) {
        Call<List<List<ReadPostItem>>> call = RetrofitClientInstance.getRetrofitInstance()
                .create(Api.class).getPostContent(id);
        call.enqueue(new Callback<List<List<ReadPostItem>>>() {
            @Override
            public void onResponse(Call<List<List<ReadPostItem>>> call, Response<List<List<ReadPostItem>>> response) {
                if (response.isSuccessful()) {
                    uiCallBack.onRequestSuccessful(response, code);
                } else if (500 <= response.code() && response.code() <= 599) {
                    uiCallBack.onInternalErrorFailure(code);
                } else if (response.code() != 401) {
                    uiCallBack.onRequestError(response, code);
                }
            }

            @Override
            public void onFailure(Call<List<List<ReadPostItem>>> call, Throwable t) {
                uiCallBack.onRequestSendFailure(t, code);
            }
        });
    }

    public static void requestAddFullPost(
            UiCallBack uiCallBack,
            int code,
            String title,
            String photoUrl,
            String category,
            String writer,
            String content1,
            String content2,
            String mainContent,
            String summary
    ) {
        Call<Object> call = RetrofitClientInstance.getRetrofitInstance()
                .create(Api.class).addFullPost(title, photoUrl, category,
                        writer, content1, content2, mainContent, summary);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    uiCallBack.onRequestSuccessful(response, code);
                } else if (500 <= response.code() && response.code() <= 599) {
                    uiCallBack.onInternalErrorFailure(code);
                } else if (response.code() != 401) {
                    uiCallBack.onRequestError(response, code);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                uiCallBack.onRequestSendFailure(t, code);
            }
        });
    }

    public static void requestGetCourse(String courseId, UiCallBack uiCallBack, int code) {
        Call<List<CourseModel>> call = RetrofitClientInstance.getRetrofitInstance()
                .create(Api.class).getCourse(courseId);
        call.enqueue(new Callback<List<CourseModel>>() {
            @Override
            public void onResponse(Call<List<CourseModel>> call, Response<List<CourseModel>> response) {
                if (response.isSuccessful()) {
                    uiCallBack.onRequestSuccessful(response, code);
                } else if (500 <= response.code() && response.code() <= 599) {
                    uiCallBack.onInternalErrorFailure(code);
                } else if (response.code() != 401) {
                    uiCallBack.onRequestError(response, code);
                }
            }

            @Override
            public void onFailure(Call<List<CourseModel>> call, Throwable t) {
                uiCallBack.onRequestSendFailure(t, code);
            }
        });
    }

    public static void requestAddCourse(
            String title,
            String picture,
            String description,
            String posts,
            UiCallBack uiCallBack,
            int code
    ) {
        Call<Object> call = RetrofitClientInstance.getRetrofitInstance()
                .create(Api.class).addCourse(title, picture, description, posts);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    uiCallBack.onRequestSuccessful(response, code);
                } else if (500 <= response.code() && response.code() <= 599) {
                    uiCallBack.onInternalErrorFailure(code);
                } else if (response.code() != 401) {
                    uiCallBack.onRequestError(response, code);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                uiCallBack.onRequestSendFailure(t, code);
            }
        });
    }

    public static void requestGetCoursePosts(String courseId, UiCallBack uiCallBack, int code) {
        Call<List<PostsListItem.PostItem>> call = RetrofitClientInstance.getRetrofitInstance()
                .create(Api.class).getCoursePosts(courseId);
        call.enqueue(new Callback<List<PostsListItem.PostItem>>() {
            @Override
            public void onResponse(Call<List<PostsListItem.PostItem>> call, Response<List<PostsListItem.PostItem>> response) {
                if (response.isSuccessful()) {
                    uiCallBack.onRequestSuccessful(response, code);
                } else if (500 <= response.code() && response.code() <= 599) {
                    uiCallBack.onInternalErrorFailure(code);
                } else if (response.code() != 401) {
                    uiCallBack.onRequestError(response, code);
                }
            }

            @Override
            public void onFailure(Call<List<PostsListItem.PostItem>> call, Throwable t) {
                uiCallBack.onRequestSendFailure(t, code);
            }
        });
    }

}
