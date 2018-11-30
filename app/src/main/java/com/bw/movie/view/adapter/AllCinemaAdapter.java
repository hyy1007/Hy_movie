package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.AllCinemaBean;
import com.bw.movie.view.holder.CinemaHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/8
 */
public class AllCinemaAdapter extends RecyclerView.Adapter<CinemaHolder> {

    onItemClickListener onItemClickListener;
    private List<AllCinemaBean.ResultBean> list;
    private Context context;

    public AllCinemaAdapter(List<AllCinemaBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CinemaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CinemaHolder(LayoutInflater.from(context).inflate(R.layout.cinema_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getLogo());
        holder.simp_view.setImageURI(uri);
        holder.text_name.setText(list.get(position).getName());
        holder.text_mi.setText(list.get(position).getDistance()+"kg");
        holder.text_address.setText(list.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface onItemClickListener{
        void ItemClick(int position);
    }

    public void setOnItemClickListener(AllCinemaAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
