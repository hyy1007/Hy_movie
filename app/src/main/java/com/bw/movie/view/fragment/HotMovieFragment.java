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
import com.bw.movie.model.bean.Eb_DetailsBean;
import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.presenter.HotMoviePresenter;
import com.bw.movie.view.activity.DetailsActivity;
import com.bw.movie.view.adapter.HotListAdapter;
import com.bw.movie.view.iview.HotMovieView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author hyy
 * @date 2018/11/5
 */
public class HotMovieFragment extends Fragment implements HotMovieView {

    Unbinder unbinder;
    @BindView(R.id.recyc_view_hotlist)
    RecyclerView recycViewHotlist;
    private HotMoviePresenter hotMoviePresenter;
    private Eb_DetailsBean eb_detailsBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_fragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hotMoviePresenter = new HotMoviePresenter();
        hotMoviePresenter.attachView(this);
        hotMoviePresenter.getHotMovieData();
        eb_detailsBean = new Eb_DetailsBean();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        hotMoviePresenter.detachView();
    }

    @Override
    public void hotMovieSuccess(final HotMovieBean hotMovieBean) {
        recycViewHotlist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        HotListAdapter hotListAdapter = new HotListAdapter(hotMovieBean, getActivity());
        recycViewHotlist.setAdapter(hotListAdapter);
        hotListAdapter.setOnItemClickListener(new HotListAdapter.onItemClickListener() {
            @Override
            public void ItemClick(int position) {
                String id = hotMovieBean.getResult().get(position).getId();
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                eb_detailsBean.setId(id);
                Log.i("movieid1", "onCreate: "+id);
                EventBus.getDefault().postSticky(eb_detailsBean);
                startActivity(intent);
            }
        });
    }

    @Override
    public void hotMovieError() {

    }
}
