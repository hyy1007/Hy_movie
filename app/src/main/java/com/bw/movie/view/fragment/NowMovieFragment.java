package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.NowMovieBean;
import com.bw.movie.presenter.NowMoviePresenter;
import com.bw.movie.view.adapter.NowListAdapter;
import com.bw.movie.view.iview.NowMovieView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author hyy
 * @date 2018/11/5
 */
public class NowMovieFragment extends Fragment implements NowMovieView {
    @BindView(R.id.recyc_view_nowlist)
    RecyclerView recycViewNowlist;
    Unbinder unbinder;
    private NowMoviePresenter nowMoviePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.now_fragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nowMoviePresenter = new NowMoviePresenter();
        nowMoviePresenter.attachView(this);
        nowMoviePresenter.getNowMovieData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        nowMoviePresenter.detachView();
    }

    @Override
    public void nowMovieSuccess(List<NowMovieBean.ResultBean> result) {
        recycViewNowlist.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        NowListAdapter nowListAdapter = new NowListAdapter(result, getActivity());
        recycViewNowlist.setAdapter(nowListAdapter);
    }

    @Override
    public void nowMovieError() {

    }
}
