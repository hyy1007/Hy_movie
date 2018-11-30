package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.view.holder.HotHolder;

/**
 * @author hyy
 * @date 2018/11/3
 */
public class HotAdapter extends RecyclerView.Adapter<HotHolder> {

    private HotMovieBean list;
    private Context context;

    public HotAdapter(HotMovieBean list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotHolder(LayoutInflater.from(context).inflate(R.layout.recyc_hotmovie_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HotHolder holder, int position) {

        Uri uri = Uri.parse(list.getResult().get(position).getImageUrl());
        holder.simp_view_hot.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.getResult().size();
    }
}
