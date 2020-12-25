package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.RecyclerAdapterPostsVertical;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;
import com.leasy.leasyAndroid.model.PostsListItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;


public class AddPostsToCourseFragment extends Fragment implements UiCallBack {
    
    private RecyclerView recyclerPosts;

    private RecyclerAdapterPostsVertical recyclerAdapterPosts;
    private String writer;
    private List<PostsListItem> postsList;
    private CreateCourseFragment createCourseFragment;

    public static AddPostsToCourseFragment newInstance(String writer, CreateCourseFragment courseFragment) {
        AddPostsToCourseFragment fragment = new AddPostsToCourseFragment();
        fragment.writer = writer;
        fragment.createCourseFragment = courseFragment;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_posts_to_course, container, false);
        initialize(v);

        if (postsList == null)
            postsList = new ArrayList<>();
        ApiUtils.requestGetAllPosts(this, null, writer);
        return v;
    }

    private void selectedClickListener(View v) {
        // TODO: 12/16/20
    }

    private void initialize(View v) {
        recyclerPosts = v.findViewById(R.id.recycler_add_posts_to_course);
    }

    @Override
    public void onRequestSuccessful(Response response) {
        List<PostsListItem.PostItem> postItems = ((List<PostsListItem.PostItem>) response.body());
        for (PostsListItem.PostItem postItem : postItems) {
            postsList.add(new PostsListItem(postItem));
        }
        recyclerAdapterPosts = new RecyclerAdapterPostsVertical(
                v -> {
                    int i = ((RecyclerView.ViewHolder) v.getTag()).getAdapterPosition();
                    PostsListItem.PostItem postItem = postsList.get(i).getPostItem();
                    createCourseFragment.addPost(postItem);
                    getActivity().onBackPressed();
                },
                postsList,
                getContext()
        );
        recyclerPosts.setAdapter(recyclerAdapterPosts);
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