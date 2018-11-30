package com.bw.movie.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.bw.movie.R;

/**
 * @author hyy
 * @date 2018/11/3
 */
public class WillHolder extends RecyclerView.ViewHolder {

    public SimpleDraweeView simp_view_will;

    public WillHolder(View itemView) {
        super(itemView);
        simp_view_will = itemView.findViewById(R.id.simp_view_will);
    }
}
