package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.bw.movie.R;

public class SkipActivity extends AppCompatActivity {

    private int time=3;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                if (time>0){
                    time--;
                    handler.sendEmptyMessageDelayed(0,1000);
                }else {
                    Intent intent = new Intent(SkipActivity.this, ShowActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    };
    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip);
        handler.sendEmptyMessageDelayed(0,1000);
        //沉浸式
        ImmersionBar.with(this)
                .init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null)
            immersionBar.destroy();
    }
}
