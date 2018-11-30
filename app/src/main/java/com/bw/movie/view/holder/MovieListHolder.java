package com.bw.movie.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.bw.movie.R;

/**
 * @author hyy
 * @date 2018/11/6
 */
public class MovieListHolder extends RecyclerView.ViewHolder {

    public SimpleDraweeView simp_view_movie;
    public TextView text_movie_name;
    public TextView text_movie_detils;
    public ImageView img_like;

    public MovieListHolder(View itemView) {
        super(itemView);
        simp_view_movie = itemView.findViewById(R.id.simp_view_movie);
        text_movie_name = itemView.findViewById(R.id.text_movie_name);
        text_movie_detils = itemView.findViewById(R.id.text_movie_detils);
        img_like = itemView.findViewById(R.id.img_like);
    }
}
