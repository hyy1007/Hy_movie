package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.NearbyCinemaBean;
import com.bw.movie.view.holder.CinemaHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/8
 */
public class NearByAdapter extends RecyclerView.Adapter<CinemaHolder> {

    private List<NearbyCinemaBean.ResultBean.NearbyCinemaListBean> list;
    private Context context;
    onItemClickListener onItemClickListener;

    public NearByAdapter(List<NearbyCinemaBean.ResultBean.NearbyCinemaListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CinemaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CinemaHolder(LayoutInflater.from(context).inflate(R.layout.cinema_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaHolder holder, final int position) {
        Uri uri = Uri.parse(list.get(position).getLogo());
        holder.simp_view.setImageURI(uri);
        holder.text_name.setText(list.get(position).getName());
        holder.text_mi.setText(list.get(position).getDistance()+"km");
        holder.text_address.setText(list.get(position).getAddress());
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

    public void setOnItemClickListener(NearByAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
