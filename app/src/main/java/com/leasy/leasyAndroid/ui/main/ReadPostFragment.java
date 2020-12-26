package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.RecyclerAdapterReadPost;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;
import com.leasy.leasyAndroid.model.PostsListItem;
import com.leasy.leasyAndroid.model.ReadPostItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Response;

public class ReadPostFragment extends Fragment implements UiCallBack {

    private TextView txtTitle, txtAuthor, txtDate;
    private CircleImageView imgAuthorPhoto;
    private ImageView imgCoverImage;
    private RecyclerView recyclerPost;

    private RecyclerAdapterReadPost adapterReadPost;
    private List<ReadPostItem> readPostItemList;

    private static final String ARG_PARAM_POST_ITEM = "param1";

    private PostsListItem paramPostItem;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param paramPostItem Parameter 1.
     * @return A new instance of fragment ReadPostFragment.
     */
    public static ReadPostFragment newInstance(PostsListItem paramPostItem) {
        ReadPostFragment fragment = new ReadPostFragment();
        fragment.setParamPostItem(paramPostItem);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_read_post, container, false);
        initialize(v);

        txtTitle.setText(paramPostItem.getPostItem().getTitle());
        txtDate.setText(paramPostItem.getPostItem().getDate());
        txtAuthor.setText(paramPostItem.getPostItem().getAuthor());
        String authorUrl = paramPostItem.getPostItem().getAuthorImageURL();
        if (URLUtil.isValidUrl(authorUrl))
            Glide.with(getContext()).load(authorUrl).into(imgAuthorPhoto);
        String coverUrl = paramPostItem.getPostItem().getPostImageURL();
        if (URLUtil.isValidUrl(coverUrl))
            Glide.with(getContext()).load(coverUrl).into(imgCoverImage);

        // FIXME: 11/2/20 Show read content
        ApiUtils.requestGetPostContent(this, 0, paramPostItem.getPostItem().getId());


        return v;
    }

    private void setupList() {
        adapterReadPost = new RecyclerAdapterReadPost(readPostItemList, getContext());
        recyclerPost.setHasFixedSize(true);
        recyclerPost.setAdapter(adapterReadPost);
    }

    @Override
    public void onRequestSuccessful(Response response, int code) {
        ReadPostItem readPostItem = ((List<List<ReadPostItem>>) response.body()).get(0).get(0);
        String content = readPostItem.getMainContent();
        System.out.println(content);
        readPostItemList = new LinkedList<>();
        readPostItemList.add(new ReadPostItem.ReadPostItemText(0, paramPostItem.getPostItem().getSummary()));
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(content);
            Iterator<String> iter = jsonObject.keys();
            int i = 1;
            while (iter.hasNext()) {
                String key = iter.next();
                if (key.startsWith("TEXT")) {
                    try {
                        ReadPostItem.ReadPostItemText itemText = new ReadPostItem.ReadPostItemText(i,
                                jsonObject.getString(key));
                        readPostItemList.add(itemText);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (key.startsWith("IMAGE")) {
                    try {
                        ReadPostItem.ReadPostItemImage readPostItemImage = new ReadPostItem.ReadPostItemImage(i,
                                jsonObject.getString(key));
                        readPostItemList.add(readPostItemImage);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                i++;
            }
            setupList();
        } catch (Exception e) {
            readPostItemList.add(new ReadPostItem.ReadPostItemText(1, content));
            System.out.println("shit");
            setupList();
            return;
        }
    }

    @Override
    public void onRequestError(Response response, int code) {

    }

    @Override
    public void onRequestSendFailure(Throwable t, int code) {
        t.printStackTrace();
    }

    @Override
    public void onRefreshTokenExpired(Response response, int code) {
        Toast.makeText(getContext(), "refresh token failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onObtainAccessTokenError(Response response, int code) {
        Toast.makeText(getContext(), "obtain access token error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onObtainAccessTokenFailure(Throwable t, int code) {
        Toast.makeText(getContext(), "access token failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInternalErrorFailure(int code) {
        Toast.makeText(getContext(), "Internal Error", Toast.LENGTH_SHORT).show();
    }

    private void initialize(View v) {
        recyclerPost = v.findViewById(R.id.recycler_read_post_recycler);
        txtAuthor = v.findViewById(R.id.txt_read_post_author_name);
        txtDate = v.findViewById(R.id.txt_read_post_date);
        txtTitle = v.findViewById(R.id.txt_read_post_title);
        imgAuthorPhoto = v.findViewById(R.id.img_read_post_author_image);
        imgCoverImage = v.findViewById(R.id.img_read_post_cover_image);
    }

    public PostsListItem getParamPostItem() {
        return paramPostItem;
    }

    public void setParamPostItem(PostsListItem paramPostItem) {
        this.paramPostItem = paramPostItem;
    }
}