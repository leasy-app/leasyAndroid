package com.leasy.leasyAndroid.model;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("Name")
    private String categoryName;

    @SerializedName("Photo")
    private String imageUrl;

    public Category() {
    }

    public Category(String categoryName, String imageUrl) {
        this.categoryName = categoryName;
        this.imageUrl = imageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
