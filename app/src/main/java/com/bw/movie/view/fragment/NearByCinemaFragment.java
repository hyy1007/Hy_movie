package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.NearbyCinemaBean;
import com.bw.movie.presenter.NearByPresenter;
import com.bw.movie.view.activity.CinemaDetailsActivioty;
import com.bw.movie.view.adapter.NearByAdapter;
import com.bw.movie.view.iview.NearByView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author hyy
 * @date 2018/11/8
 */
public class NearByCinemaFragment extends Fragment implements NearByView {

    @BindView(R.id.recyc_view_nearby)
    RecyclerView recycViewNearby;
    Unbinder unbinder;
    private NearByPresenter nearByPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cinema_nearby_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nearByPresenter = new NearByPresenter();
        nearByPresenter.attachView(this);
        nearByPresenter.getNearByData();
    }

    @Override
    public void nearbySuccess(final List<NearbyCinemaBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList) {
        recycViewNearby.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        NearByAdapter nearByAdapter = new NearByAdapter(nearbyCinemaList,getActivity());
        recycViewNearby.setAdapter(nearByAdapter);
        nearByAdapter.setOnItemClickListener(new NearByAdapter.onItemClickListener() {
            @Override
            public void ItemClick(int position) {
                String id = nearbyCinemaList.get(position).getId();
                Log.i("cinemaId1", "onCreate: "+id);
                Intent intent = new Intent(getActivity(), CinemaDetailsActivioty.class);
                intent.putExtra("cinemaId",id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void nearbyError() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        nearByPresenter.detachView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
