package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bw.movie.R;
import com.bw.movie.model.bean.DetailsBean;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * @author hyy
 * @date 2018/11/17
 */
public class PrevueAdapter extends RecyclerView.Adapter<PrevueAdapter.PrevueHolder> {


    private List<DetailsBean.ResultBean.ShortFilmListBean> list;
    private Context context;

    public PrevueAdapter(List<DetailsBean.ResultBean.ShortFilmListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PrevueHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrevueHolder(LayoutInflater.from(context).inflate(R.layout.prevue_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PrevueHolder holder, int position) {
        holder.videoplayer.setUp(list.get(position).getVideoUrl(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
        holder.videoplayer.thumbImageView.setImageURI(Uri.parse(list.get(position).getImageUrl()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PrevueHolder extends RecyclerView.ViewHolder {


        private final JZVideoPlayerStandard videoplayer;

        public PrevueHolder(View itemView) {
            super(itemView);
            videoplayer = itemView.findViewById(R.id.videoplayer);
        }
    }
}
