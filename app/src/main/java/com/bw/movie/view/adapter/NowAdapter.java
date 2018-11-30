package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.NowMovieBean;
import com.bw.movie.view.holder.NowHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/3
 */
public class NowAdapter extends RecyclerView.Adapter<NowHolder> {

    private List<NowMovieBean.ResultBean> list;
    private Context context;


    public NowAdapter(List<NowMovieBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NowHolder(LayoutInflater.from(context).inflate(R.layout.recyc_nowmovie_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NowHolder holder, int position) {
        Uri parse = Uri.parse(list.get(position).getImageUrl());
        holder.simp_view_now.setImageURI(parse);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
