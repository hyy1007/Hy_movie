package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.bw.movie.R;
import com.bw.movie.model.bean.EventBus_MovieBean;
import com.bw.movie.view.adapter.MovieListAdapter;
import com.bw.movie.view.fragment.HotMovieFragment;
import com.bw.movie.view.fragment.NowMovieFragment;
import com.bw.movie.view.fragment.WillMovieFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.frame_layout)
    ViewPager frameLayout;
    private ImmersionBar immersionBar;
    private HotMovieFragment hotMovieFragment;
    private NowMovieFragment nowMovieFragment;
    private WillMovieFragment willMovieFragment;
    private FragmentManager fragmentManager;
    private String saveName;

    //当前显示的fragment
    private static final String STATE_FRAGMENT_SHOW = "STATE_FRAGMENT_SHOW";
    private Fragment currentFragment = new Fragment();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);

        //more();
        EventBus.getDefault().register(this);
        //沉浸式
        ImmersionBar.with(this)
                .init();

        //初始化fragment
        hotMovieFragment = new HotMovieFragment();
        nowMovieFragment = new NowMovieFragment();
        willMovieFragment = new WillMovieFragment();

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);

        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(hotMovieFragment);
        fragments.add(nowMovieFragment);
        fragments.add(willMovieFragment);
        MovieListAdapter movieListAdapter = new MovieListAdapter(getSupportFragmentManager(), fragments);
        frameLayout.setAdapter(movieListAdapter);

    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void Event(EventBus_MovieBean eventBus_movieBean) {
        int m = eventBus_movieBean.getMsg();
        Log.i("mmm", "Event: "+m);
        if (m == 1) {
            frameLayout.setCurrentItem(0);
        } else if (m == 2) {
            frameLayout.setCurrentItem(1);
        } else if (m == 3) {
            frameLayout.setCurrentItem(2);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null)
            immersionBar.destroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb1:
                frameLayout.setCurrentItem(0);
                break;
            case R.id.rb2:
                frameLayout.setCurrentItem(1);
                break;
            case R.id.rb3:
                frameLayout.setCurrentItem(2);
                break;

        }
    }


}
