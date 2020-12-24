package com.leasy.leasyAndroid.model;

import com.google.gson.annotations.SerializedName;

public class CourseModel {
    @SerializedName("Id")
    private String courseID;

    @SerializedName("Name")
    private String title;

    @SerializedName("Picture")
    private String description;

    @SerializedName("Explanation")
    private String coverURL;

    private String author;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
