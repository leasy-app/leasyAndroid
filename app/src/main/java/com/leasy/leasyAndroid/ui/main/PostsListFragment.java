package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.leasy.leasyAndroid.MainActivity;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.RecyclerAdapterPostsVertical;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;
import com.leasy.leasyAndroid.model.PostsListItem;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Response;

public class PostsListFragment extends Fragment implements UiCallBack, View.OnClickListener {

    private RecyclerView recyclerView;
    private MaterialToolbar toolbar;

    private String category;
    private LinkedList<PostsListItem> postsListItemList;
    private RecyclerAdapterPostsVertical recyclerAdapter;

    public static PostsListFragment newInstance(String category) {
        PostsListFragment fragment = new PostsListFragment();
        fragment.category = category;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_posts_list, container, false);
        initialize(v);
        toolbar.setTitle(category);
        if (postsListItemList == null) {
            ApiUtils.requestGetAllPosts(this, null);
        } else {
            recyclerAdapter = new RecyclerAdapterPostsVertical(this::onClick, postsListItemList, getContext());
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(recyclerAdapter);
        }

        return v;
    }

    private void initialize(View v) {
        recyclerView = v.findViewById(R.id.recycler_posts_list_fragment);
        toolbar = v.findViewById(R.id.toolbar_posts_list_title);
    }

    @Override
    public void onClick(View v) {
        int i = ((RecyclerView.ViewHolder) v.getTag()).getAdapterPosition();
        ((MainActivity) getActivity()).showPost(postsListItemList.get(i));
    }

    @Override
    public void onRequestSuccessful(Response response) {
        postsListItemList = new LinkedList<PostsListItem>();
        for (PostsListItem.PostItem post :((List<PostsListItem.PostItem>) response.body())){
            Log.i("logloglog", post.toString());
            postsListItemList.add(new PostsListItem(post));
        }
        recyclerAdapter = new RecyclerAdapterPostsVertical(this::onClick, postsListItemList, getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onRequestError(Response response) {

    }

    @Override
    public void onRequestSendFailure(Throwable t) {

    }

    @Override
    public void onRefreshTokenExpired(Response response) {

    }

    @Override
    public void onObtainAccessTokenError(Response response) {

    }

    @Override
    public void onObtainAccessTokenFailure(Throwable t) {

    }

    @Override
    public void onInternalErrorFailure() {

    }
}