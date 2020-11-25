package com.leasy.leasyAndroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.leasy.leasyAndroid.model.Category;

import java.util.List;

public class CategoriesListRecyclerAdapter extends RecyclerView.Adapter<CategoriesListRecyclerAdapter.CategoriesListViewHolder> {

    private final List<Category> categoryList;
    private final View.OnClickListener onClickListener;
    private Context context;

    public CategoriesListRecyclerAdapter(List<Category> categoryList, View.OnClickListener onClickListener, Context context) {
        this.categoryList = categoryList;
        this.onClickListener = onClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoriesListViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.categories_list_item, parent, false),
                onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesListViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if (category.getImageUrl() != null) {
            if (URLUtil.isValidUrl(category.getImageUrl()))
                Glide.with(context).load(category.getImageUrl()).into(holder.imgCover);
        }
        holder.txtName.setText(category.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    static class CategoriesListViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCover;
        TextView txtName;

        public CategoriesListViewHolder(@NonNull View itemView, View.OnClickListener onClickListener) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_categories_list_category_name);
            imgCover = itemView.findViewById(R.id.img_categories_list_category_cover);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }
    }
}
