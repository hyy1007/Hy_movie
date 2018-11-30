package com.bw.movie.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;

/**
 * @author hyy
 * @date 2018/11/16
 */
public class CinemaDetailsHolder extends RecyclerView.ViewHolder {

    public TextView text_hall;
    public TextView text_price_cinema;
    public TextView text_begintime;
    public TextView text_endtime;
    public ImageView img_next_cinema;

    public CinemaDetailsHolder(View itemView) {
        super(itemView);
        text_hall = itemView.findViewById(R.id.text_hall);
        text_price_cinema = itemView.findViewById(R.id.text_price_cinema);
        text_begintime = itemView.findViewById(R.id.text_begintime);
        text_endtime = itemView.findViewById(R.id.text_endtime);
        img_next_cinema = itemView.findViewById(R.id.img_next_cinema);
    }
}
