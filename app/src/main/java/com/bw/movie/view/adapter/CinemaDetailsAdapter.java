package com.bw.movie.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.CinemaDetailsListBean;
import com.bw.movie.view.holder.CinemaDetailsHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/16
 */
public class CinemaDetailsAdapter extends RecyclerView.Adapter<CinemaDetailsHolder> {

    private List<CinemaDetailsListBean.ResultBean> list;
    private Context context;
    onItemClickListaner onItemClickListaner;

    public CinemaDetailsAdapter(List<CinemaDetailsListBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CinemaDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CinemaDetailsHolder(LayoutInflater.from(context).inflate(R.layout.cinema_details_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaDetailsHolder holder, final int position) {
        holder.text_begintime.setText(list.get(position).getBeginTime());
        holder.text_endtime.setText(list.get(position).getEndTime());
        holder.text_hall.setText(list.get(position).getScreeningHall());
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

    public void setOnItemClickListaner(CinemaDetailsAdapter.onItemClickListaner onItemClickListaner) {
        this.onItemClickListaner = onItemClickListaner;
    }
}
