package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.bw.movie.R;
import com.bw.movie.view.adapter.FollowAdapter;
import com.bw.movie.view.fragment.FollowCinemaFragment;
import com.bw.movie.view.fragment.FollowMovieFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LikeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.frame_layout)
    ViewPager frameLayout;
    private ImmersionBar immersionBar;
    private FollowMovieFragment followMovieFragment;
    private FollowCinemaFragment followCinemaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
        ButterKnife.bind(this);
        //沉浸式
        ImmersionBar.with(this)
                .init();

        //初始化fragment
        followMovieFragment = new FollowMovieFragment();
        followCinemaFragment = new FollowCinemaFragment();

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);

        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(followMovieFragment);
        fragments.add(followCinemaFragment);

        FollowAdapter followAdapter = new FollowAdapter(getSupportFragmentManager(),fragments);
        frameLayout.setAdapter(followAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null)
            immersionBar.destroy();
    }

    @Override
    public void onClick(View v) {

    }
}
