package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leasy.leasyAndroid.CategoriesListRecyclerAdapter;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.model.Category;

import java.util.LinkedList;

public class CategoriesListFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerCategories;
    private CategoriesListRecyclerAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_categories_list, container, false);
        initialize(v);

        // FIXME: 11/2/20 Show real data
        LinkedList<Category> categories = new LinkedList<>();
        categories.add(new Category("Algorithms", null));
        categories.add(new Category("Algorithms2", null));
        categories.add(new Category("Algorithms3", null));
        recyclerAdapter = new CategoriesListRecyclerAdapter(categories, this, getContext());
        recyclerCategories.setHasFixedSize(true);
        recyclerCategories.setAdapter(recyclerAdapter);

        return v;
    }

    @Override
    public void onClick(View v) {
        // TODO: 11/2/20
    }

    private void initialize(View v) {
        recyclerCategories = v.findViewById(R.id.recycler_categories_list);
    }
}