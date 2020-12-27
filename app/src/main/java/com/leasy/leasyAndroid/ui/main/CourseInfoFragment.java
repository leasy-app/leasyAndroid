package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;
import com.leasy.leasyAndroid.model.CourseModel;
import com.leasy.leasyAndroid.util.ImageLoader;

import java.util.List;

import retrofit2.Response;

public class CourseInfoFragment extends Fragment implements UiCallBack {

    private String courseID;

    private TextView txtDescription, txtTitle, txtAuthor, txtShowPosts;
    private ImageView imgCover, imgShowPosts, imgAuthor;
    
    public static CourseInfoFragment newInstance(String courseID) {
        CourseInfoFragment fragment = new CourseInfoFragment();
        fragment.courseID = courseID;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course_info, container, false);
        initialize(v);

        ApiUtils.requestGetCourse(courseID, this, 0);

        imgShowPosts.setOnClickListener(this::showPosts);
        txtShowPosts.setOnClickListener(this::showPosts);
        
        return v;
    }

    private void showPosts(View v) {
        ((ViewCourseFragment) getParentFragment()).showCoursePosts(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    @Override
    public void onRequestSuccessful(Response response, int code) {
        CourseModel courseModel = ((List<CourseModel>) response.body()).get(0);
        txtTitle.setText(courseModel.getTitle());
        txtDescription.setText(courseModel.getDescription());
        ImageLoader.loadImage(courseModel.getCoverURL(), imgCover);
        // TODO: 12/26/20 images
    }

    @Override
    public void onRequestError(Response response, int code) {

    }

    @Override
    public void onRequestSendFailure(Throwable t, int code) {

    }

    @Override
    public void onRefreshTokenExpired(Response response, int code) {

    }

    @Override
    public void onObtainAccessTokenError(Response response, int code) {

    }

    @Override
    public void onObtainAccessTokenFailure(Throwable t, int code) {

    }

    @Override
    public void onInternalErrorFailure(int code) {

    }

    private void initialize(View v) {
        txtAuthor = v.findViewById(R.id.txt_course_info_author_name);
        txtTitle = v.findViewById(R.id.txt_course_info_title);
        txtDescription = v.findViewById(R.id.txt_course_description);
        txtShowPosts = v.findViewById(R.id.txt_course_content);
        imgAuthor = v.findViewById(R.id.img_course_info_author_image);
        imgShowPosts = v.findViewById(R.id.img_course_info_arrow_down);
        imgCover = v.findViewById(R.id.img_course_info_cover_image);
    }
}