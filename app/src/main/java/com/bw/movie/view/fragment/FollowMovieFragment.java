package com.bw.movie.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.bw.movie.model.bean.FollowMovieListBean;
import com.bw.movie.presenter.FollowMovieListPresenter;
import com.bw.movie.view.adapter.FollowMovieAdapter;
import com.bw.movie.view.iview.FollowMovieListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author hyy
 * @date 2018/11/22
 */
public class FollowMovieFragment extends Fragment implements FollowMovieListView {

    @BindView(R.id.recyc_follow_movie)
    RecyclerView recycFollowMovie;
    Unbinder unbinder;
    private FollowMovieListPresenter followMovieListPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.followmovie, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        followMovieListPresenter = new FollowMovieListPresenter();
         followMovieListPresenter.attachView(this);
        SharedPreferences sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String userId = sp.getString("userId", "");
        String sessionId = sp.getString("sessionId", "");
        Log.i("userid3", "onActivityCreated: "+userId);
        followMovieListPresenter.getFollowMovieListData(userId,sessionId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        followMovieListPresenter.detachView();
    }

    @Override
    public void followMovieListSuccess(List<FollowMovieListBean.ResultBean> result) {
        recycFollowMovie.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        FollowMovieAdapter followMovieAdapter = new FollowMovieAdapter(result,getActivity());
        recycFollowMovie.setAdapter(followMovieAdapter);
    }

    @Override
    public void followMovieListError() {

    }
}
