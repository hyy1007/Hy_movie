package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.bw.movie.R;
import com.bw.movie.view.fragment.CinemaFragment;
import com.bw.movie.view.fragment.MineFragment;
import com.bw.movie.view.fragment.MovieFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    private ImmersionBar immersionBar;
    private MovieFragment movieFragment;
    private CinemaFragment cinemaFragment;
    private MineFragment mineFragment;
    private FragmentManager fragmentManager;
    private String saveName;
    //当前显示的fragment
    private static final String STATE_FRAGMENT_SHOW = "STATE_FRAGMENT_SHOW";

    private Fragment currentFragment = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        //沉浸式
        ImmersionBar.with(this)
                .init();

        //初始化fragment对象
        movieFragment = new MovieFragment();
        cinemaFragment = new CinemaFragment();
        mineFragment = new MineFragment();

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);

        //获取管理者
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            //获取“内存重启”时保存的fragment名字
            saveName = savedInstanceState.getString(STATE_FRAGMENT_SHOW);
            //从fragmentManager里面找到fragment

            movieFragment = (MovieFragment) fragmentManager.findFragmentByTag(MovieFragment.class.getName());

            cinemaFragment = (CinemaFragment) fragmentManager.findFragmentByTag(CinemaFragment.class.getName());

            mineFragment = (MineFragment) fragmentManager.findFragmentByTag(MineFragment.class.getName());
            if (TextUtils.isEmpty(saveName)) {
                //解决重叠问题  1
                fragmentManager.beginTransaction()
                        .show(movieFragment)
                        .hide(cinemaFragment)
                        .hide(mineFragment)
                        .commit();

                //把当前显示的fragment记录下来
                currentFragment = movieFragment;
            } else {
                if (saveName.equals(movieFragment.getClass().getName())) {
                    fragmentManager.beginTransaction()
                            .show(movieFragment)
                            .hide(cinemaFragment)
                            .hide(mineFragment)
                            .commit();

                    //把当前显示的fragment记录下来
                    currentFragment = movieFragment;
                } else if (saveName.equals(cinemaFragment.getClass().getName())) {
                    fragmentManager.beginTransaction()
                            .show(cinemaFragment)
                            .hide(movieFragment)
                            .hide(mineFragment)
                            .commit();

                    //把当前显示的fragment记录下来
                    currentFragment = cinemaFragment;
                } else if (saveName.equals(mineFragment.getClass().getName())) {
                    fragmentManager.beginTransaction()
                            .show(mineFragment)
                            .hide(movieFragment)
                            .hide(cinemaFragment)
                            .commit();

                    //把当前显示的fragment记录下来
                    currentFragment = mineFragment;
                }

            }

        } else {//正常启动时调用
            movieFragment = new MovieFragment();
            cinemaFragment = new CinemaFragment();
            mineFragment = new MineFragment();
            showFragment(movieFragment);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null)
            immersionBar.destroy();
    }

    /**
     * fragment的显示
     *
     * @param fg
     */
    private void showFragment(Fragment fg) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //如果之前没有添加过
        if (!fg.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.frame_layout, fg, fg.getClass().getName());  //第三个参数为添加当前的fragment时绑定一个tag，即绑定fragment的类名
        } else {
            transaction
                    .hide(currentFragment)
                    .show(fg);
        }

        currentFragment = fg;

        transaction.commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //“内存重启”时保存当前的fragment名字
        outState.putString(STATE_FRAGMENT_SHOW, currentFragment.getClass().getName());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb1:
                showFragment(movieFragment);
                break;
            case R.id.rb2:
                showFragment(cinemaFragment);
                break;
            case R.id.rb3:
                showFragment(mineFragment);
                break;

        }
    }
}
