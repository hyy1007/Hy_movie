package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.bw.movie.R;
import com.bw.movie.view.adapter.MyPagerAdapter;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager view_pager;
    private List<Integer> list;
    private Button btn_skip_show;
    private ImmersionBar immersionBar;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_pager = findViewById(R.id.view_pager);
        btn_skip_show = findViewById(R.id.btn_skip_show);
        //沉浸式
        ImmersionBar.with(this)
                .init();
        /**
         * 首次进入欢迎页面,以后直接进主页
         */
        sp = getSharedPreferences("WelCome", MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        if(isFirst==true){

            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isFirst", false);
            editor.commit();
        }else{

            Intent intent = new Intent(this,SkipActivity.class);
            startActivity(intent);
            finish();

        }
        list = new ArrayList<>();
        list.add(R.drawable.y1);
        list.add(R.drawable.y2);
        list.add(R.drawable.y3);
        list.add(R.drawable.y4);
        MyPagerAdapter adapter = new MyPagerAdapter(this,list);
        view_pager.setAdapter(adapter);

        view_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(MainActivity.this,"text"+position,Toast.LENGTH_SHORT).show();
                if (position==3){
                    btn_skip_show.setVisibility(View.VISIBLE);
                    AlphaAnimation aa = new AlphaAnimation(0, 1f);
                    aa.setDuration(1000);
                    btn_skip_show.setAnimation(aa);
                    btn_skip_show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }else {
                    btn_skip_show.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null)
            immersionBar.destroy();
    }
}
