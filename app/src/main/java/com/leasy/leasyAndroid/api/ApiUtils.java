package com.leasy.leasyAndroid.api;

import android.content.Context;

import com.leasy.leasyAndroid.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiUtils {

    private final static String LOG_TAG = "API_UTILS_TAG";

    public static void requestGetAllCategories(UiCallBack uiCallBack) {
        Call<List<Category>> call = RetrofitClientInstance.getRetrofitInstance()
                .create(Api.class).getAllCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    uiCallBack.onRequestSuccessful(response);
                } else if (500 <= response.code() && response.code() <= 599) {
                    uiCallBack.onInternalErrorFailure();
                } else if (response.code() != 401) {
                    uiCallBack.onRequestError(response);
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                uiCallBack.onRequestSendFailure(t);
            }
        });
    }

}
