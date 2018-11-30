package com.bw.movie.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;

/**
 * @author hyy
 * @date 2018/11/17
 */
public class ReviewHolder extends RecyclerView.ViewHolder {

    public ImageView simp_user;
    public TextView tv_commentcontent;
    public TextView tv_praisenum;
    public TextView tv_username;

    public ReviewHolder(View itemView) {
        super(itemView);
        simp_user = itemView.findViewById(R.id.simp_user);
        tv_commentcontent = itemView.findViewById(R.id.tv_commentcontent);
        tv_praisenum = itemView.findViewById(R.id.tv_praisenum);
        tv_username = itemView.findViewById(R.id.tv_username);
    }
}
