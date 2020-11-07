package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
    private List<PostsListItem> postsListItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(v);

        Glide.with(this).load(R.drawable.profile).centerInside().into(imgUser);


        // FIXME: 10/31/20 Get the real list

//        LinkedList<ListItems> list = new LinkedList<>();
//        ListItems items = new ListItems();
//        items.setSectionTitle("Latest Posts");
//        list.add(items);
        if (postsListItemList == null) {
            ApiUtils.requestGetAllPosts(this, null);
        } else {
            recyclerAdapter = new RecyclerAdapterPostsVertical(this::onClick, postsListItemList, getContext());
            recyclerPosts.setHasFixedSize(true);
            recyclerPosts.setAdapter(recyclerAdapter);
        }

        return v;
    }

    @Override
    public void onClick(View v) {
        // TODO: 10/31/20 open posts
    }

    private void initialize(View v) {
        imgUser = v.findViewById(R.id.img_user_image_home);
        recyclerPosts = v.findViewById(R.id.recycler_home_page);
    }

    @Override
    public void onRequestSuccessful(Response response) {
        postsListItemList = new LinkedList<>();
        postsListItemList.add(new PostsListItem("Latest Posts"));
        for (PostsListItem.PostItem post :((List<PostsListItem.PostItem>) response.body())){
            Log.i("logloglog", post.toString());
            postsListItemList.add(new PostsListItem(post));
        }
        recyclerAdapter = new RecyclerAdapterPostsVertical(this::onClick, postsListItemList, getContext());
        recyclerPosts.setHasFixedSize(true);
        recyclerPosts.setAdapter(recyclerAdapter);
    }

    @Override
    public void onRequestError(Response response) {

        Toast.makeText(getContext(), "req error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestSendFailure(Throwable t) {
        Toast.makeText(getContext(), "send error", Toast.LENGTH_LONG).show();
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