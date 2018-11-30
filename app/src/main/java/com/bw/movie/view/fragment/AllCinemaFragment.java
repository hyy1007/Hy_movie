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
import com.bw.movie.model.bean.AllCinemaBean;
import com.bw.movie.presenter.AllCinemaPresenter;
import com.bw.movie.view.adapter.AllCinemaAdapter;
import com.bw.movie.view.iview.AllCinemaView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author hyy
 * @date 2018/11/8
 */
public class AllCinemaFragment extends Fragment implements AllCinemaView {
    @BindView(R.id.recyc_view_all)
    RecyclerView recycViewAll;
    Unbinder unbinder;
    private AllCinemaPresenter allCinemaPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cinema_all_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        allCinemaPresenter = new AllCinemaPresenter();
        allCinemaPresenter.attachView(this);
        allCinemaPresenter.getAllCinemaData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        allCinemaPresenter.detachView();
    }

    @Override
    public void allCinemaSuccess(List<AllCinemaBean.ResultBean> result) {
        recycViewAll.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        AllCinemaAdapter allCinemaAdapter = new AllCinemaAdapter(result,getActivity());
        recycViewAll.setAdapter(allCinemaAdapter);
    }

    @Override
    public void allCinemaError() {

    }
}
