package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.FollowMovieListBean;
import com.bw.movie.view.holder.FollowMovieHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/22
 */
public class FollowMovieAdapter extends RecyclerView.Adapter<FollowMovieHolder> {

    onItemClickListener onItemClickListener;
    private List<FollowMovieListBean.ResultBean> list;
    private Context context;

    public FollowMovieAdapter(List<FollowMovieListBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FollowMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FollowMovieHolder(LayoutInflater.from(context).inflate(R.layout.follow_movie_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FollowMovieHolder holder, final int position) {
        Uri uri = Uri.parse(list.get(position).getImageUrl());
        holder.my_movie_item_icon.setImageURI(uri);
        holder.my_movie_item_jianjie.setText(list.get(position).getSummary());
        holder.my_movie_item_name.setText(list.get(position).getName());
        holder.my_movie_item_time.setText((int) list.get(position).getReleaseTime());
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.ItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public interface onItemClickListener{
        void ItemClick(int position);
    }

    public void setOnItemClickListener(FollowMovieAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
