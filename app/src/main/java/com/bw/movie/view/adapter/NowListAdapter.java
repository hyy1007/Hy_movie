package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.NowMovieBean;
import com.bw.movie.view.holder.MovieListHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/6
 */
public class NowListAdapter extends RecyclerView.Adapter<MovieListHolder> {

    private List<NowMovieBean.ResultBean> list;
    private Context context;
    onItemClickListener onItemClickListener;

    public NowListAdapter(List<NowMovieBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieListHolder(LayoutInflater.from(context).inflate(R.layout.list_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListHolder holder, final int position) {
        Uri uri = Uri.parse(list.get(position).getImageUrl());
        holder.simp_view_movie.setImageURI(uri);
        holder.text_movie_name.setText(list.get(position).getName());
        holder.text_movie_detils.setText(list.get(position).getSummary());
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
        return list.size();
    }

    public interface onItemClickListener{
        void ItemClick(int position);
    }

    public void setOnItemClickListener(NowListAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
