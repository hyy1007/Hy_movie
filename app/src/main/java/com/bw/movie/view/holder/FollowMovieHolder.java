package com.bw.movie.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.bw.movie.R;

/**
 * @author hyy
 * @date 2018/11/22
 */
public class FollowMovieHolder extends RecyclerView.ViewHolder {

    public SimpleDraweeView my_movie_item_icon;
    public TextView my_movie_item_jianjie;
    public TextView my_movie_item_name;
    public TextView my_movie_item_time;

    public FollowMovieHolder(View itemView) {
        super(itemView);
        my_movie_item_icon = itemView.findViewById(R.id.my_movie_item_icon);
        my_movie_item_jianjie = itemView.findViewById(R.id.my_movie_item_jianjie);
        my_movie_item_name = itemView.findViewById(R.id.my_movie_item_name);
        my_movie_item_time = itemView.findViewById(R.id.my_movie_item_time);
    }
}
