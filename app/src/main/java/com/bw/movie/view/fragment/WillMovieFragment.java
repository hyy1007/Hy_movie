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
import com.bw.movie.model.bean.WillMovieBean;
import com.bw.movie.presenter.WillMoviePresenter;
import com.bw.movie.view.adapter.WillListAdapter;
import com.bw.movie.view.iview.WillMovieView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author hyy
 * @date 2018/11/5
 */
public class WillMovieFragment extends Fragment implements WillMovieView {
    @BindView(R.id.recyc_view_willlist)
    RecyclerView recycViewWilllist;
    Unbinder unbinder;
    private WillMoviePresenter willMoviePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.will_fragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        willMoviePresenter = new WillMoviePresenter();
        willMoviePresenter.attachView(this);
        willMoviePresenter.getWillMovieData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        willMoviePresenter.detachView();
    }

    @Override
    public void willMovieSuccess(List<WillMovieBean.ResultBean> result) {
        recycViewWilllist.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        WillListAdapter willListAdapter = new WillListAdapter(result,getActivity());
        recycViewWilllist.setAdapter(willListAdapter);
    }

    @Override
    public void willMovieError() {

    }
}
