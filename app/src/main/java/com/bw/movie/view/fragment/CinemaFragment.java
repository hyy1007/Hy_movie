package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.view.adapter.FragAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author hyy
 * @date 2018/11/3
 */
public class CinemaFragment extends Fragment {
    @BindView(R.id.text_location_cinema)
    TextView textLocationCinema;
    @BindView(R.id.text_search_cinema)
    TextView textSearchCinema;
    @BindView(R.id.btn_nearby)
    TextView btnNearby;
    @BindView(R.id.btn_all)
    TextView btnAll;
    @BindView(R.id.view_pager_cinema)
    ViewPager viewPagerCinema;
    Unbinder unbinder;
    private NearByCinemaFragment nearByCinemaFragment;
    private AllCinemaFragment allCinemaFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cinema_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nearByCinemaFragment = new NearByCinemaFragment();
        allCinemaFragment = new AllCinemaFragment();
        //构造适配器
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(nearByCinemaFragment);
        fragments.add(allCinemaFragment);
        FragAdapter adapter = new FragAdapter(getActivity().getSupportFragmentManager(), fragments);
        viewPagerCinema.setAdapter(adapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_nearby, R.id.btn_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_nearby:
                viewPagerCinema.setCurrentItem(0);
                break;
            case R.id.btn_all:
                viewPagerCinema.setCurrentItem(1);
                break;
        }
    }
}
