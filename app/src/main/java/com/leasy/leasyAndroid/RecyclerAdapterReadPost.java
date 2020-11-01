package com.leasy.leasyAndroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.leasy.leasyAndroid.model.ReadPostItem;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class RecyclerAdapterReadPost extends RecyclerView.Adapter<RecyclerAdapterReadPost.ReadPostRecyclerViewHolder> {

    private final List<ReadPostItem> readPostItemList;
    private Context context;
//    private Lifecycle lifecycle;

    public RecyclerAdapterReadPost(List<ReadPostItem> readPostItemList, Context context) {
        this.readPostItemList = readPostItemList;
        this.context = context;
//        this.lifecycle = lifecycle;
    }

    @NonNull
    @Override
    public ReadPostRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ReadPostItem.TypeEnum.Text.ordinal()) {
            return new ReadPostRecyclerViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.read_posts_body_text_item,
                            parent,
                            false),
                    ReadPostItem.TypeEnum.Text
            );
        } else {
            return new ReadPostRecyclerViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.read_posts_image_item,
                            parent,
                            false),
                    ReadPostItem.TypeEnum.Image
            );
        } /*else {
            return new ReadPostRecyclerViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.read_posts_body_text_item,
                            parent,
                            false),
                    ReadPostItem.TypeEnum.Youtube
            );
        }*/
    }

    @Override
    public void onBindViewHolder(@NonNull ReadPostRecyclerViewHolder holder, int position) {
        switch (readPostItemList.get(position).getType()) {
            case Text:
                holder.txtText.setText(((ReadPostItem.ReadPostItemText) (readPostItemList).get(position)).getText());
                break;
            case Image:
                Glide.with(context).load(((ReadPostItem.ReadPostItemImage) (readPostItemList).get(position)).getImageURL())
                        .into(holder.imgImage);
                break;
//            case Youtube:
//                lifecycle.addObserver(holder.youtube);
//                holder.youtube.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//                    @Override
//                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                        youTubePlayer.loadVideo(
//                                ((ReadPostItem.ReadPostItemYoutube) readPostItemList
//                                        .get(position))
//                                        .getYoutubeID(),
//                                0);
//                    }
//                });
//                break;
        }
    }

    @Override
    public int getItemCount() {
        return readPostItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return readPostItemList.get(position).getType().ordinal();
    }
     static class ReadPostRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView txtText;
        ImageView imgImage;
//        YouTubePlayerView youtube;

        public ReadPostRecyclerViewHolder(@NonNull View itemView, ReadPostItem.TypeEnum type) {
            super(itemView);
            switch (type) {
                case Text:
                    txtText = itemView.findViewById(R.id.txt_read_post_item_body_text);
                    break;
                case Image:
                    imgImage = itemView.findViewById(R.id.img_read_post_image_item);
                    break;
//                case Youtube:
//                    youtube = itemView.findViewById(R.id.youtube_read_post_item);
//                    break;
            }
        }
    }
}
