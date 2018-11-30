package com.bw.movie.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.bw.movie.R;

/**
 * @author hyy
 * @date 2018/11/8
 */
public class CinemaHolder extends RecyclerView.ViewHolder {

    public TextView text_address;
    public TextView text_mi;
    public TextView text_name;
    public ImageView img_like_cinema;
    public SimpleDraweeView simp_view;

    public CinemaHolder(View itemView) {
        super(itemView);
        text_address = itemView.findViewById(R.id.text_address);
        text_mi = itemView.findViewById(R.id.text_mi);
        text_name = itemView.findViewById(R.id.text_name);
        simp_view = itemView.findViewById(R.id.simp_view);
        img_like_cinema = itemView.findViewById(R.id.img_like_cinema);
    }
}
