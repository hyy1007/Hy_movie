package com.bw.movie.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.bw.movie.R;
import com.bw.movie.model.bean.DetailsBean;
import com.bw.movie.model.bean.Eb_DetailsBean;
import com.bw.movie.model.bean.FollowMovieBean;
import com.bw.movie.model.bean.ReviewListBean;
import com.bw.movie.presenter.DetailsPresenter;
import com.bw.movie.presenter.FollowMoviePresenter;
import com.bw.movie.presenter.ReviewListPresenter;
import com.bw.movie.view.adapter.PhotoAdapter;
import com.bw.movie.view.adapter.PrevueAdapter;
import com.bw.movie.view.adapter.ReviewAdapter;
import com.bw.movie.view.iview.DetailsView;
import com.bw.movie.view.iview.FollowMovieView;
import com.bw.movie.view.iview.ReviewListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsView, View.OnClickListener, ReviewListView, FollowMovieView {

    @BindView(R.id.btn_action)
    Button btnAction;
    @BindView(R.id.text_action)
    TextView textAction;
    @BindView(R.id.btn_heartxiangqing)
    ImageView btnHeartxiangqing;
    @BindView(R.id.text_xiangname)
    TextView textXiangname;
    @BindView(R.id.simp_xiangview)
    SimpleDraweeView simpXiangview;
    @BindView(R.id.btn_xiang)
    Button btnXiang;
    @BindView(R.id.btn_yugao)
    Button btnYugao;
    @BindView(R.id.btn_photo)
    Button btnPhoto;
    @BindView(R.id.btn_ping)
    Button btnPing;
    @BindView(R.id.btn_fan)
    Button btnFan;
    @BindView(R.id.btn_gou)
    Button btnGou;
    private DetailsPresenter detailsPresenter;
    private ImmersionBar immersionBar;
    private String id;
    private LayoutInflater inflater;
    private String name;
    private SimpleDraweeView simp_details_pop;
    private TextView text_details_actor;
    private TextView text_details_address;
    private TextView text_details_intro;
    private TextView text_details_time;
    private TextView text_name_pop;
    private TextView text_details_type;
    private DetailsBean.ResultBean result;
    private PopupWindow window4;
    private PopupWindow window3;
    private PopupWindow window2;
    private PopupWindow window1;
    private View view1;
    private RecyclerView review_recyc_view;
    private ReviewListPresenter reviewListPresenter;
    private RecyclerView photo_recyc_view;
    private RecyclerView prevue_recyc_view;
    private FollowMoviePresenter followMoviePresenter;
    private String userId;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        detailsPresenter = new DetailsPresenter();
        detailsPresenter.attachView(this);
        detailsPresenter.getDetailsData(id);
        //沉浸式
        ImmersionBar.with(this)
                .init();
        btnXiang.setOnClickListener(this);
        btnYugao.setOnClickListener(this);
        btnPing.setOnClickListener(this);
        btnPhoto.setOnClickListener(this);

        showPopupWindow4();
        showPopupWindow3();
        showPopupWindow2();
        showPopupWindow1();

        btnFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //关注
        SharedPreferences user = DetailsActivity.this.getSharedPreferences("user", MODE_PRIVATE);
        userId = user.getString("userId", "");
        sessionId = user.getString("sessionId", "");
        //Log.e("userId3", "onViewClicked: "+ userId);
        //Log.w("sessionId3", "onViewClicked: "+ sessionId);
        followMoviePresenter = new FollowMoviePresenter();
        followMoviePresenter.attachView(this);

    }

    private void showPopupWindow1() {

        //详情
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view1 = inflater.inflate(R.layout.detailspop, null);
        window1 = new PopupWindow(view1,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window1.setFocusable(true);

        ColorDrawable dw1 = new ColorDrawable(0xffffffff);
        window1.setBackgroundDrawable(dw1);

        window1.setAnimationStyle(R.style.PopupAnimation);
        //初始化控件
        simp_details_pop = view1.findViewById(R.id.simp_details_pop);
        text_details_actor = view1.findViewById(R.id.text_details_actor);
        text_details_address = view1.findViewById(R.id.text_details_address);
        text_details_intro = view1.findViewById(R.id.text_details_intro);
        text_details_time = view1.findViewById(R.id.text_details_time);
        text_name_pop = view1.findViewById(R.id.text_name_pop);
        text_details_type = view1.findViewById(R.id.text_details_type);

    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void event(Eb_DetailsBean eb_detailsBean) {
        id = eb_detailsBean.getId();
        Log.i("movieid2", "onCreate: " + id);
    }

    @Override
    public void detailsSuccess(DetailsBean detailsBean) {
        result = detailsBean.getResult();
        name = detailsBean.getResult().getName();
        textAction.setText(name);
        textXiangname.setText(detailsBean.getResult().getName());
        Uri uri = Uri.parse(detailsBean.getResult().getImageUrl());
        simpXiangview.setImageURI(uri);
        //电影名字
        text_name_pop.setText(result.getName());
        //电影类型
        text_details_type.setText("类型：" + result.getMovieTypes());
        //导演
        text_details_actor.setText("导演：" + result.getDirector());
        //时长
        text_details_time.setText("时长：" + result.getDuration());
        //产地
        text_details_address.setText("产地：" + result.getPlaceOrigin());
        //详情
        text_details_intro.setText(result.getSummary());
        //Uri uri = Uri.parse(result.getImageUrl());
        simp_details_pop.setImageURI(uri);
        //预告
        prevue_recyc_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        PrevueAdapter prevueAdapter = new PrevueAdapter(result.getShortFilmList(), this);
        prevue_recyc_view.setAdapter(prevueAdapter);
        //剧照
        photo_recyc_view.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        PhotoAdapter photoAdapter = new PhotoAdapter(result.getPosterList(), this);
        photo_recyc_view.setAdapter(photoAdapter);

        final boolean followMovie = result.isFollowMovie();
        btnHeartxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (followMovie == true) {
                    followMoviePresenter.getFollowMovieBean(userId, sessionId, id);
                }
            }
        });

        if (result.isFollowMovie()) {
            btnHeartxiangqing.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            btnHeartxiangqing.setImageResource(R.mipmap.com_icon_collection_selected_hdpi);
        }

    }

    private void showPopupWindow2() {
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view2 = inflater.inflate(R.layout.prevuepop, null);
        window2 = new PopupWindow(view2,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window2.setFocusable(true);

        ColorDrawable dw2 = new ColorDrawable(0xffffffff);
        window2.setBackgroundDrawable(dw2);

        window2.setAnimationStyle(R.style.PopupAnimation);
        prevue_recyc_view = view2.findViewById(R.id.prevue_recyc_view);
    }

    private void showPopupWindow3() {
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view3 = inflater.inflate(R.layout.photopop, null);
        window3 = new PopupWindow(view3,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window3.setFocusable(true);

        ColorDrawable dw3 = new ColorDrawable(0xffffffff);
        window3.setBackgroundDrawable(dw3);

        window3.setAnimationStyle(R.style.PopupAnimation);
        photo_recyc_view = view3.findViewById(R.id.photo_recyc_view);
    }

    private void showPopupWindow4() {
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view4 = inflater.inflate(R.layout.reviewpop, null);
        window4 = new PopupWindow(view4,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window4.setFocusable(true);

        ColorDrawable dw4 = new ColorDrawable(0xffffffff);
        window4.setBackgroundDrawable(dw4);

        window4.setAnimationStyle(R.style.PopupAnimation);
        reviewListPresenter = new ReviewListPresenter();
        reviewListPresenter.attachView(this);
        reviewListPresenter.getReviewListData(id);
        review_recyc_view = view4.findViewById(R.id.review_recyc_view);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_xiang:
                window1.showAtLocation(DetailsActivity.this.findViewById(R.id.btn_xiang),
                        Gravity.BOTTOM, 0, 0);
                //info_recycle_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
               /* InfoAdapter infoAdapter = new InfoAdapter();
                info_recycle_view.setAdapter(infoAdapter);*/
                break;
            case R.id.btn_yugao:
                window2.showAtLocation(DetailsActivity.this.findViewById(R.id.btn_yugao),
                        Gravity.BOTTOM, 0, 0);
                break;

            case R.id.btn_photo:
                window3.showAtLocation(DetailsActivity.this.findViewById(R.id.btn_photo),
                        Gravity.BOTTOM, 0, 0);
                break;
            case R.id.btn_ping:
                window4.showAtLocation(DetailsActivity.this.findViewById(R.id.btn_ping),
                        Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    @Override
    public void detailsError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null)
            immersionBar.destroy();
        EventBus.getDefault().unregister(this);
        detailsPresenter.detachView();
        reviewListPresenter.detachView();
        followMoviePresenter.detachView();
    }


    @Override
    public void reviewListSuccess(List<ReviewListBean.ResultBean> result) {
        review_recyc_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ReviewAdapter reviewAdapter = new ReviewAdapter(result, this);
        review_recyc_view.setAdapter(reviewAdapter);
    }

    @Override
    public void reviewListError() {

    }

    @Override
    public void followMovieSuccess(FollowMovieBean followMovieBean) {
        String message = followMovieBean.getMessage();
        String status = followMovieBean.getStatus();
        if (status.equals("0000")) {
            btnHeartxiangqing.setImageResource(R.mipmap.com_icon_collection_selected_hdpi);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
        detailsPresenter.getDetailsData(id);
    }

    @Override
    public void followMovieError() {

    }
}
