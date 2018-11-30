package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.WillMovieBean;
import com.bw.movie.view.holder.MovieListHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/6
 */
public class WillListAdapter extends RecyclerView.Adapter<MovieListHolder> {

    private List<WillMovieBean.ResultBean> list;
    private Context context;
    onItemClickListaner onItemClickListaner;

    public WillListAdapter(List<WillMovieBean.ResultBean> list, Context context) {
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
        if (onItemClickListaner!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListaner.ItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface onItemClickListaner{
        void ItemClick(int position);
    }

    public void setOnItemClickListaner(WillListAdapter.onItemClickListaner onItemClickListaner) {
        this.onItemClickListaner = onItemClickListaner;
    }
}
