package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.view.holder.MovieListHolder;

/**
 * @author hyy
 * @date 2018/11/6
 */
public class HotListAdapter extends RecyclerView.Adapter<MovieListHolder> {

    private HotMovieBean list;
    private Context context;
    onItemClickListener onItemClickListener;

    public HotListAdapter(HotMovieBean list, Context context) {
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
        Uri uri = Uri.parse(list.getResult().get(position).getImageUrl());
        holder.simp_view_movie.setImageURI(uri);
        holder.text_movie_name.setText(list.getResult().get(position).getName());
        holder.text_movie_detils.setText(list.getResult().get(position).getSummary());
        if (list.getResult().get(position).isFollowMovie()){
            holder.img_like.setImageResource(R.drawable.ic_favorite_black_24dp);
        }else {
            holder.img_like.setImageResource(R.mipmap.com_icon_collection_selected_hdpi);
        }
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
        return list.getResult().size();
    }

    public interface onItemClickListener{
        void ItemClick(int position);
    }

    public void setOnItemClickListener(HotListAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
