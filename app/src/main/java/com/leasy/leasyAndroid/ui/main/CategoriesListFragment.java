package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leasy.leasyAndroid.CategoriesListRecyclerAdapter;
import com.leasy.leasyAndroid.MainActivity;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;
import com.leasy.leasyAndroid.model.Category;

import java.util.List;

import retrofit2.Response;

public class CategoriesListFragment extends Fragment implements View.OnClickListener, UiCallBack {

    private RecyclerView recyclerCategories;
    private CategoriesListRecyclerAdapter recyclerAdapter;
    private List<Category> categoryList;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_categories_list, container, false);
        initialize(v);

        if (categoryList == null) {
            swipeRefreshLayout.setRefreshing(true);
            ApiUtils.requestGetAllCategories(this);
        }

        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (categoryList != null)
                swipeRefreshLayout.setRefreshing(false);
            ApiUtils.requestGetAllCategories(this);
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        int i = ((RecyclerView.ViewHolder) v.getTag()).getAdapterPosition();
        String category = categoryList.get(i).getCategoryName();
        ((MainActivity) getActivity()).showCategoryPosts(category);
    }

    private void initialize(View v) {
        recyclerCategories = v.findViewById(R.id.recycler_categories_list);
        swipeRefreshLayout = v.findViewById(R.id.swipe_refresh_categories_layout);
    }

    @Override
    public void onRequestSuccessful(Response response) {
        categoryList = ((List<Category>) response.body());
        recyclerAdapter = new CategoriesListRecyclerAdapter(categoryList, this, getContext());
        recyclerCategories.setHasFixedSize(true);
        recyclerCategories.setAdapter(recyclerAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRequestError(Response response) {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRequestSendFailure(Throwable t) {
        Toast.makeText(getContext(), "send failure", Toast.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false);
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
        Toast.makeText(getContext(), "internal error", Toast.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false);
    }
}