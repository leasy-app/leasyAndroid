package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.leasy.leasyAndroid.App;
import com.leasy.leasyAndroid.CoursesListRecyclerAdapter;
import com.leasy.leasyAndroid.MainActivity;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.RecyclerAdapterPostsVertical;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;
import com.leasy.leasyAndroid.model.CourseModel;
import com.leasy.leasyAndroid.model.PostsListItem;
import com.leasy.leasyAndroid.model.User;
import com.leasy.leasyAndroid.util.ImageLoader;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener, UiCallBack {

    private ImageView imgUser;
    private TextView name;
    private RecyclerView recyclerPosts, recyclerCourses;
    private RecyclerAdapterPostsVertical postsRecyclerAdapter;
    private CoursesListRecyclerAdapter coursesRecyclerAdapter;
    private SwipeRefreshLayout refreshLayout;
    private List<PostsListItem> postsListItemList;
    private List<CourseModel> coursesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(v);
        refreshLayout.setRefreshing(true);

        ApiUtils.getUser(new UiCallBack() {
            @Override
            public void onRequestSuccessful(Response response, int code) {
                User u = ((List<User>)response.body()).get(0);
                name.setText(u.name);
                ImageLoader.loadImage(u.photo, imgUser);
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
        }, 1, App.username());

        if (postsListItemList == null) {
            ApiUtils.requestGetAllPosts(this,0, null, null);
        } else {
            postsRecyclerAdapter = new RecyclerAdapterPostsVertical(this::onClick, postsListItemList, getContext());
            recyclerPosts.setHasFixedSize(true);
            recyclerPosts.setAdapter(postsRecyclerAdapter);
            refreshLayout.setRefreshing(false);
        }

        if (coursesList == null) {
            ApiUtils.requestGetCourse(null, this, 1);
        } else {
            coursesRecyclerAdapter = new CoursesListRecyclerAdapter(coursesList, getContext(), this::courseClicked);
            recyclerCourses.setHasFixedSize(true);
            recyclerCourses.setAdapter(coursesRecyclerAdapter);
            refreshLayout.setRefreshing(false);
        }

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ApiUtils.requestGetAllPosts(HomeFragment.this, 0,null, null);
            }
        });

        return v;
    }

    public void courseClicked(View v){
        int i = ((RecyclerView.ViewHolder) v.getTag()).getAdapterPosition();
        ((MainActivity) getActivity()).showCourse(coursesList.get(i));
    }

    @Override
    public void onClick(View v) {
        int i = ((RecyclerView.ViewHolder) v.getTag()).getAdapterPosition();
        ((MainActivity) getActivity()).showPost(postsListItemList.get(i));
    }

    private void initialize(View v) {
        refreshLayout = v.findViewById(R.id.swipe_refresh_home_posts);
        imgUser = v.findViewById(R.id.img_user_image_home);
        name = v.findViewById(R.id.txt_user_name_home);
        recyclerPosts = v.findViewById(R.id.recycler_home_page);
        recyclerCourses = v.findViewById(R.id.recycler_courses_home);
    }

    @Override
    public void onRequestSuccessful(Response response, int code) {
        if (code == 0) {
            postsListItemList = new LinkedList<>();
            for (PostsListItem.PostItem post : ((List<PostsListItem.PostItem>) response.body())) {
//            Log.i("logloglog", post.toString());
                postsListItemList.add(new PostsListItem(post));
            }
            postsRecyclerAdapter = new RecyclerAdapterPostsVertical(this::onClick, postsListItemList, getContext());
            recyclerPosts.setHasFixedSize(true);
            recyclerPosts.setAdapter(postsRecyclerAdapter);
            refreshLayout.setRefreshing(false);
        } else {
            coursesList = ((List<CourseModel>) response.body());
            coursesRecyclerAdapter = new CoursesListRecyclerAdapter(coursesList,
                    getContext(),
                    this::courseClicked);
            recyclerCourses.setHasFixedSize(true);
            recyclerCourses.setAdapter(coursesRecyclerAdapter);
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRequestError(Response response, int code) {
        refreshLayout.setRefreshing(false);

        Toast.makeText(getContext(), "req error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestSendFailure(Throwable t, int code) {
        Toast.makeText(getContext(), "send error", Toast.LENGTH_LONG).show();
        refreshLayout.setRefreshing(false);

    }

    @Override
    public void onRefreshTokenExpired(Response response, int code) {
        refreshLayout.setRefreshing(false);

    }

    @Override
    public void onObtainAccessTokenError(Response response, int code) {
        refreshLayout.setRefreshing(false);

    }

    @Override
    public void onObtainAccessTokenFailure(Throwable t, int code) {
        refreshLayout.setRefreshing(false);

    }

    @Override
    public void onInternalErrorFailure(int code) {
        refreshLayout.setRefreshing(false);

    }
}