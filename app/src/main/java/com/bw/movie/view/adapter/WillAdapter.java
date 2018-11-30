package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.WillMovieBean;
import com.bw.movie.view.holder.WillHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/3
 */
public class WillAdapter extends RecyclerView.Adapter<WillHolder> {

    private List<WillMovieBean.ResultBean> list;
    private Context context;

    public WillAdapter(List<WillMovieBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public WillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WillHolder(LayoutInflater.from(context).inflate(R.layout.recyc_willmovie_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WillHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getImageUrl());
        holder.simp_view_will.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
