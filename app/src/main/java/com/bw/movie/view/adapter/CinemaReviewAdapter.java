package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.CinemaReviewBean;
import com.bw.movie.view.holder.ReviewHolder;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/19
 */
public class CinemaReviewAdapter extends RecyclerView.Adapter<ReviewHolder> {

    onItemClickListener onItemClickListener;
    private List<CinemaReviewBean.ResultBean> list;
    private Context context;

    public CinemaReviewAdapter(List<CinemaReviewBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_comment,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, final int position) {
        Uri uri = Uri.parse(list.get(position).getCommentHeadPic());
        holder.simp_user.setImageURI(uri);
        holder.tv_commentcontent.setText(list.get(position).getCommentContent());
        holder.tv_praisenum.setText(""+list.get(position).getGreatNum());
        holder.tv_username.setText(list.get(position).getCommentUserName());
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

    public void setOnItemClickListener(CinemaReviewAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
