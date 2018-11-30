package com.bw.movie.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.WillMovieBean;

import java.util.List;

public class setMyCdAdapter extends RecyclerView.Adapter<setMyCdAdapter.CdViewHolder> {

    private Context context;
    private List<WillMovieBean.ResultBean> list;

    public setMyCdAdapter(Context context, List<WillMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.tu_item, null);
        CdViewHolder cdViewHolder = new CdViewHolder(inflate);
        return cdViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CdViewHolder holder, int position) {

        Glide.with(context)
                .load(list.get(position % list.size()).getImageUrl())
                .into(holder.simp_tu_view);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    class CdViewHolder extends RecyclerView.ViewHolder
    {


        private final ImageView simp_tu_view;

        public CdViewHolder(View itemView) {
            super(itemView);
            simp_tu_view = (ImageView)itemView.findViewById(R.id.simp_tu_view);
        }
    }

    private RecyclerItemListener listener;

    public interface RecyclerItemListener
    {
        void onClick(int position);
    }

    public void setListener(RecyclerItemListener listener)
    {
        this.listener = listener;
    }
}
