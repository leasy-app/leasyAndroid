package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leasy.leasyAndroid.MainActivity;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.RecyclerAdapterPostsVertical;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;
import com.leasy.leasyAndroid.model.PostsListItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class CoursePostsFragment extends Fragment implements UiCallBack {

   private String courseID;

   private RecyclerView recyclerPosts;
   private TextView txtGoInfo;
   private ImageView imgGoInfo;

   private List<PostsListItem> postItems;
   private RecyclerAdapterPostsVertical recyclerAdapterPostsVertical;

    public static CoursePostsFragment newInstance(String courseID) {
        CoursePostsFragment fragment = new CoursePostsFragment();
        fragment.courseID = courseID;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course_posts, container, false);
        initialize(v);

//        if (postItems == null)
            ApiUtils.requestGetCoursePosts(courseID, this, 0);

        txtGoInfo.setOnClickListener(v1 -> {
            Log.d("asd", "clicked");
            ((ViewCourseFragment) getParentFragment()).showCourseInfo(R.anim.slide_in_down, R.anim.slide_out_down);
        });
        imgGoInfo.setOnClickListener(v1 -> {
            Log.d("asd", "clicked");
            ((ViewCourseFragment) getParentFragment()).showCourseInfo(R.anim.slide_in_down, R.anim.slide_out_down);
        });

        return v;
    }

    private void showInfo(View v) {
        Log.d("asd", "clicked");
        ((ViewCourseFragment) getParentFragment()).showCourseInfo(R.anim.slide_in_down, R.anim.slide_out_down);
    }

    @Override
    public void onRequestSuccessful(Response response, int code) {
       List<PostsListItem.PostItem> postItemList = ((List<PostsListItem.PostItem>) response.body());
       postItems = new ArrayList<>();
        for (PostsListItem.PostItem item :
                postItemList) {
            postItems.add(new PostsListItem(item));
        }
       recyclerAdapterPostsVertical = new RecyclerAdapterPostsVertical(v -> {
           int i = ((RecyclerView.ViewHolder) v.getTag()).getAdapterPosition();
           ((MainActivity) getActivity()).showPost(postItems.get(i));
       },
               postItems,
               getContext());
        recyclerPosts.setAdapter(recyclerAdapterPostsVertical);
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
        imgGoInfo = v.findViewById(R.id.img_course_posts_arrow_up);
        txtGoInfo = v.findViewById(R.id.txt_course_info);
        recyclerPosts = v.findViewById(R.id.recycler_view_course_posts);
    }
}