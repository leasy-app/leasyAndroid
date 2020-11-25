package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.leasy.leasyAndroid.MainActivity;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.RecyclerAdapterPostsVertical;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;
import com.leasy.leasyAndroid.model.PostsListItem;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener, UiCallBack {

    private ImageView imgUser;
    private RecyclerView recyclerPosts;
    private RecyclerAdapterPostsVertical recyclerAdapter;
    private SwipeRefreshLayout refreshLayout;
    private List<PostsListItem> postsListItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(v);
        refreshLayout.setRefreshing(true);

        Glide.with(this).load(R.drawable.profile).centerInside().into(imgUser);

        if (postsListItemList == null) {
            ApiUtils.requestGetAllPosts(this, null);
        } else {
            recyclerAdapter = new RecyclerAdapterPostsVertical(this::onClick, postsListItemList, getContext());
            recyclerPosts.setHasFixedSize(true);
            recyclerPosts.setAdapter(recyclerAdapter);
        }

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ApiUtils.requestGetAllPosts(HomeFragment.this, null);
            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        int i = ((RecyclerView.ViewHolder) v.getTag()).getAdapterPosition();
        ((MainActivity) getActivity()).showPost(postsListItemList.get(i));
    }

    private void initialize(View v) {
        refreshLayout = v.findViewById(R.id.swipe_refresh_home_posts);
        imgUser = v.findViewById(R.id.img_user_image_home);
        recyclerPosts = v.findViewById(R.id.recycler_home_page);
    }

    @Override
    public void onRequestSuccessful(Response response) {
        postsListItemList = new LinkedList<>();
        postsListItemList.add(new PostsListItem("Latest Posts"));
        for (PostsListItem.PostItem post : ((List<PostsListItem.PostItem>) response.body())) {
            Log.i("logloglog", post.toString());
            postsListItemList.add(new PostsListItem(post));
        }
        recyclerAdapter = new RecyclerAdapterPostsVertical(this::onClick, postsListItemList, getContext());
        recyclerPosts.setHasFixedSize(true);
        recyclerPosts.setAdapter(recyclerAdapter);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRequestError(Response response) {
        refreshLayout.setRefreshing(false);

        Toast.makeText(getContext(), "req error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestSendFailure(Throwable t) {
        Toast.makeText(getContext(), "send error", Toast.LENGTH_LONG).show();
        refreshLayout.setRefreshing(false);

    }

    @Override
    public void onRefreshTokenExpired(Response response) {
        refreshLayout.setRefreshing(false);

    }

    @Override
    public void onObtainAccessTokenError(Response response) {
        refreshLayout.setRefreshing(false);

    }

    @Override
    public void onObtainAccessTokenFailure(Throwable t) {
        refreshLayout.setRefreshing(false);

    }

    @Override
    public void onInternalErrorFailure() {
        refreshLayout.setRefreshing(false);

    }
}