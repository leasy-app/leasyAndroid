package com.leasy.leasyAndroid;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leasy.leasyAndroid.model.WritePostItem;

import java.util.List;

public class WritePostRecyclerAdapter extends RecyclerView.Adapter<WritePostRecyclerAdapter.WritePostRecyclerViewHolder> {

    private List<WritePostItem> postItemList;
    private Context context;

    public WritePostRecyclerAdapter(List<WritePostItem> postItemList, Context context) {
        this.postItemList = postItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public WritePostRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == WritePostItem.WriteItemEnum.text.ordinal()) {
            return new WritePostRecyclerViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.write_post_add_text_recycler_layout, parent, false),
                    WritePostItem.WriteItemEnum.text
            );
        } else if (viewType == WritePostItem.WriteItemEnum.heading.ordinal()){
            // TODO: 11/4/20
            return null;
        } else if (viewType == WritePostItem.WriteItemEnum.image.ordinal()) {
            // TODO: 11/4/20
            return null;
        } else {
            // TODO: 11/4/20
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull WritePostRecyclerViewHolder holder, int position) {
        WritePostItem postItem = postItemList.get(position);
        WritePostItem.WriteItemEnum typeEnum = postItem.getPostType();
        switch (typeEnum) {
            case text:
//                if (((WritePostItem.WritePostItemAddText) postItem).getText() != null)
                holder.edtAddText.setText(((WritePostItem.WritePostItemAddText) postItem).getText());
                holder.edtAddText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        ((WritePostItem.WritePostItemAddText) postItemList.get(position)).setText(s.toString());
                    }
                });
                holder.btnDeleteText.setOnClickListener(v -> {
                    Toast.makeText(context, String.valueOf(position), Toast.LENGTH_LONG).show();
                    postItemList.remove(position);
                    System.out.println("position: " + position);
                    System.out.println("ls size: "+ postItemList.size());
                    System.out.println("recycler size: " + getItemCount());
                    this.notifyDataSetChanged();
                });
                break;
            case heading:
                // TODO: 11/4/20
                break;
            case image:
                // TODO: 11/4/20
                break;
            case code:
                // TODO: 11/4/20
                break;
        }
    }

    @Override
    public int getItemCount() {
        return postItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return postItemList.get(position).getPostType().ordinal();
    }

    public static class WritePostRecyclerViewHolder extends RecyclerView.ViewHolder {

        //Text item
        EditText edtAddText;
        ImageButton btnDeleteText;

        public WritePostRecyclerViewHolder(@NonNull View itemView, WritePostItem.WriteItemEnum typeEnum) {
            super(itemView);
            setIsRecyclable(false);
            switch (typeEnum) {
                case text:
                    edtAddText = itemView.findViewById(R.id.edt_write_post_add_text);
                    btnDeleteText = itemView.findViewById(R.id.btn_write_post_delete_text);
                    break;
                case image:
                    // TODO: 11/4/20
                    break;
                case code:
                    // TODO: 11/4/20
                    break;
                case heading:
                    // TODO: 11/4/20
                    break;
            }

        }
    }
}
