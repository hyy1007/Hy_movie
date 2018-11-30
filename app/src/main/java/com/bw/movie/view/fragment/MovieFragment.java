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
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.EventBus_MovieBean;
import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.model.bean.NowMovieBean;
import com.bw.movie.model.bean.WillMovieBean;
import com.bw.movie.presenter.HotMoviePresenter;
import com.bw.movie.presenter.NowMoviePresenter;
import com.bw.movie.presenter.WillMoviePresenter;
import com.bw.movie.view.activity.MapActivity;
import com.bw.movie.view.activity.MovieListActivity;
import com.bw.movie.view.adapter.HotAdapter;
import com.bw.movie.view.adapter.NowAdapter;
import com.bw.movie.view.adapter.WillAdapter;
import com.bw.movie.view.adapter.setMyCdAdapter;
import com.bw.movie.view.iview.HotMovieView;
import com.bw.movie.view.iview.NowMovieView;
import com.bw.movie.view.iview.WillMovieView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * @author hyy
 * @date 2018/11/3
 */
public class MovieFragment extends Fragment implements HotMovieView, NowMovieView, WillMovieView {
    @BindView(R.id.text_location_movie)
    TextView textLocationMovie;
    @BindView(R.id.text_search_movie)
    TextView textSearchMovie;
    @BindView(R.id.img_hot)
    ImageView imgHot;
    @BindView(R.id.recyc_view_hot)
    RecyclerView recycViewHot;
    @BindView(R.id.img_now)
    ImageView imgNow;
    @BindView(R.id.recyc_view_now)
    RecyclerView recycViewNow;
    @BindView(R.id.img_will)
    ImageView imgWill;
    @BindView(R.id.recyc_view_will)
    RecyclerView recycViewWill;
    Unbinder unbinder;
    @BindView(R.id.text_hot)
    TextView textHot;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.text_now)
    TextView textNow;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.text_will)
    TextView textWill;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.list)
    RecyclerCoverFlow list;
    private Intent intent;
    private HotMoviePresenter hotMoviePresenter;
    private NowMoviePresenter nowMoviePresenter;
    private WillMoviePresenter willMoviePresenter;
    private EventBus_MovieBean eventBus_movieBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //热门电影
        hotMoviePresenter = new HotMoviePresenter();
        hotMoviePresenter.attachView(this);
        hotMoviePresenter.getHotMovieData();
        //正在热映
        nowMoviePresenter = new NowMoviePresenter();
        nowMoviePresenter.attachView(this);
        nowMoviePresenter.getNowMovieData();
        //即将上映
        willMoviePresenter = new WillMoviePresenter();
        willMoviePresenter.attachView(this);
        willMoviePresenter.getWillMovieData();

        eventBus_movieBean = new EventBus_MovieBean();

    }

        /**
         * 点击定位,搜索
         *
         * @param view
         */
        @OnClick({R.id.text_location_movie, R.id.text_search_movie})
        public void onViewClicked (View view){
            switch (view.getId()) {
                case R.id.text_location_movie:

                    intent = new Intent(getActivity(), MapActivity.class);
                    startActivity(intent);
                    break;
                case R.id.text_search_movie:
                    break;
            }
        }


        /**
         * 热门电影
         *
         * @param
         */
        @Override
        public void hotMovieSuccess (HotMovieBean hotMovieBean) {
            recycViewHot.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            HotAdapter hotAdapter = new HotAdapter(hotMovieBean, getActivity());
            recycViewHot.setAdapter(hotAdapter);
            Log.i("bbb", "hotMovieSuccess: " + hotMovieBean);
        }

        @OnClick(R.id.img_hot)
        public void onHotClicked () {
            Intent intent = new Intent(getActivity(), MovieListActivity.class);
            eventBus_movieBean.setMsg(1);
            EventBus.getDefault().postSticky(eventBus_movieBean);
            startActivity(intent);
        }

        @Override
        public void hotMovieError () {

        }

        /**
         * 正在热映
         *
         * @param result
         */
        @Override
        public void nowMovieSuccess (List < NowMovieBean.ResultBean > result) {
            recycViewNow.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            NowAdapter nowAdapter = new NowAdapter(result, getActivity());
            recycViewNow.setAdapter(nowAdapter);
        }

        @OnClick(R.id.img_now)
        public void onNowClicked () {
            Intent intent = new Intent(getActivity(), MovieListActivity.class);
            eventBus_movieBean.setMsg(2);
            EventBus.getDefault().postSticky(eventBus_movieBean);
            startActivity(intent);
        }


        @Override
        public void nowMovieError () {

        }

        /**
         * 即将上映
         *
         * @param result
         */
        @Override
        public void willMovieSuccess (List < WillMovieBean.ResultBean > result) {
            recycViewWill.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            WillAdapter willAdapter = new WillAdapter(result, getActivity());
            recycViewWill.setAdapter(willAdapter);

            setMyCdAdapter setMyCdAdapter = new setMyCdAdapter(getActivity(), result);
            list.setAdapter(setMyCdAdapter);
            list.scrollToPosition(2);
            list.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
                @Override
                public void onItemSelected(int position) {

                }
            });
        }

        @OnClick(R.id.img_will)
        public void onWillClicked () {
            Intent intent = new Intent(getActivity(), MovieListActivity.class);
            eventBus_movieBean.setMsg(3);
            EventBus.getDefault().postSticky(eventBus_movieBean);
            startActivity(intent);
        }

        @Override
        public void willMovieError () {

        }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            unbinder.unbind();
            hotMoviePresenter.detachView();
            nowMoviePresenter.detachView();
            willMoviePresenter.detachView();
        }


    }

