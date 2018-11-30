package com.bw.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.bw.movie.R;
import com.bw.movie.model.bean.CinemaDetailsBean;
import com.bw.movie.model.bean.CinemaDetailsListBean;
import com.bw.movie.model.bean.CinemaReviewBean;
import com.bw.movie.model.bean.FindMovieByCinemaIdBean;
import com.bw.movie.model.bean.WillMovieBean;
import com.bw.movie.presenter.CinemaDetailsListPresenter;
import com.bw.movie.presenter.CinemaDetailsPresenter;
import com.bw.movie.presenter.CinemaReviewPresenter;
import com.bw.movie.presenter.FindMovieByCinemaIdPresenter;
import com.bw.movie.presenter.HotMoviePresenter;
import com.bw.movie.presenter.WillMoviePresenter;
import com.bw.movie.view.adapter.CinemaDetailsAdapter;
import com.bw.movie.view.adapter.CinemaReviewAdapter;
import com.bw.movie.view.adapter.MyCoverAdapter;
import com.bw.movie.view.iview.CinemaDetailsListView;
import com.bw.movie.view.iview.CinemaDetailsView;
import com.bw.movie.view.iview.CinemaReviewView;
import com.bw.movie.view.iview.FindMovieByCinemaIdView;
import com.bw.movie.view.iview.WillMovieView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

public class CinemaDetailsActivioty extends AppCompatActivity implements CinemaDetailsView, CinemaDetailsListView, CinemaReviewView, WillMovieView, FindMovieByCinemaIdView {

    @BindView(R.id.simp_view)
    SimpleDraweeView simpView;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_address)
    TextView textAddress;
    @BindView(R.id.cover_cinema_details)
    RecyclerCoverFlow coverCinemaDetails;
    @BindView(R.id.recyc_cinema_details)
    RecyclerView recycCinemaDetails;
    @BindView(R.id.img_return)
    ImageView imgReturn;
    @BindView(R.id.img_pop_cinema)
    ImageView imgPopCinema;
    private ImmersionBar immersionBar;
    private CinemaDetailsPresenter cinemaDetailsPresenter;
    private HotMoviePresenter hotMoviePresenter;
    private CinemaDetailsListPresenter listPresenter;
    private String cinemaId;
    private RadioGroup radio_group_cinema;
    private RadioButton rb1_cinema;
    private RadioButton rb2_cinema;
    private LinearLayout linear_details;
    private LinearLayout linear_ping;
    private LayoutInflater inflater;
    private PopupWindow window1;
    private TextView cinema_details_location;
    private TextView cinema_details_tel;
    private TextView cinema_details_bus;
    private RecyclerView cinema_review_recyc;
    private CinemaReviewPresenter cinemaReviewPresenter;
    private WillMoviePresenter willMoviePresenter;
    private FindMovieByCinemaIdPresenter idPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_details_activioty);
        ButterKnife.bind(this);
        //沉浸式
        ImmersionBar.with(this)
                .init();
        Intent intent = getIntent();
        cinemaId = intent.getStringExtra("cinemaId");
        Log.i("cinemaId2", "onCreate: " + cinemaId);
        cinemaDetailsPresenter = new CinemaDetailsPresenter();
        cinemaDetailsPresenter.attachView(this);
        cinemaDetailsPresenter.getCinemaDetailsData(cinemaId);
        listPresenter = new CinemaDetailsListPresenter();
        listPresenter.attachView(this);
        //
        willMoviePresenter = new WillMoviePresenter();
        willMoviePresenter.attachView(this);
        willMoviePresenter.getWillMovieData();
        //
        idPresenter = new FindMovieByCinemaIdPresenter();
        idPresenter.attachView(this);
        idPresenter.getFindMovieByCinemaIdData(cinemaId);
        //pop
        showPop();
        cinemaReviewPresenter = new CinemaReviewPresenter();
        cinemaReviewPresenter.attachView(this);
        cinemaReviewPresenter.getCinemaReview(cinemaId);
        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void showPop() {

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.cinemadetailspop, null);
        window1 = new PopupWindow(view1,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window1.setFocusable(true);

        ColorDrawable dw1 = new ColorDrawable(0xffffffff);
        window1.setBackgroundDrawable(dw1);

        window1.setAnimationStyle(R.style.PopupAnimation);
        radio_group_cinema =view1.findViewById(R.id.radio_group_cinema);
        rb1_cinema = view1.findViewById(R.id.rb1_cinema);
        rb2_cinema = view1.findViewById(R.id.rb2_cinema);
        linear_details = view1.findViewById(R.id.linear_details);
        linear_ping = view1.findViewById(R.id.linear_ping);
        cinema_details_location = view1.findViewById(R.id.cinema_details_location);
        cinema_details_tel = view1.findViewById(R.id.cinema_details_tel);
        cinema_details_bus = view1.findViewById(R.id.cinema_details_bus);
        cinema_review_recyc = view1.findViewById(R.id.cinema_review_recyc);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null)
            immersionBar.destroy();
        cinemaDetailsPresenter.detachView();
        listPresenter.detachView();
        cinemaReviewPresenter.detachView();
        willMoviePresenter.detachView();
        idPresenter.detachView();
    }

    @Override
    public void cinemaDetailsSuccess(CinemaDetailsBean.ResultBean result) {
        Uri uri = Uri.parse(result.getLogo());
        simpView.setImageURI(uri);
        textAddress.setText(result.getAddress());
        textName.setText(result.getName());
        cinema_details_location.setText(result.getAddress());
        cinema_details_bus.setText(result.getVehicleRoute());
        cinema_details_tel.setText(result.getPhone());
    }

    @Override
    public void cinemaDetailsError() {

    }


    @Override
    public void cinemaDetailsListSuccess(List<CinemaDetailsListBean.ResultBean> result) {
        recycCinemaDetails.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        CinemaDetailsAdapter cinemaDetailsAdapter = new CinemaDetailsAdapter(result, this);
        recycCinemaDetails.setAdapter(cinemaDetailsAdapter);
        cinemaDetailsAdapter.setOnItemClickListaner(new CinemaDetailsAdapter.onItemClickListaner() {
            @Override
            public void ItemClick(int position) {
                Intent intent = new Intent(CinemaDetailsActivioty.this, BuyTicketAcitvity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void cinemaDetailsListError() {

    }

    @OnClick(R.id.img_pop_cinema)
    public void onViewClicked() {
        window1.showAtLocation(this.findViewById(R.id.img_pop_cinema),
                Gravity.BOTTOM, 0, 0);
        radio_group_cinema.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==rb1_cinema.getId()){

                    linear_details.setVisibility(View.VISIBLE);
                    linear_ping.setVisibility(View.GONE);
                }else if (checkedId==rb2_cinema.getId()){

                    linear_details.setVisibility(View.GONE);
                    linear_ping.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void cinemaReviewSuccess(List<CinemaReviewBean.ResultBean> result) {
        cinema_review_recyc.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        CinemaReviewAdapter reviewAdapter = new CinemaReviewAdapter(result,this);
        cinema_review_recyc.setAdapter(reviewAdapter);
    }

    @Override
    public void cinemaReviewError() {

    }

    @Override
    public void willMovieSuccess(final List<WillMovieBean.ResultBean> result) {

    }

    @Override
    public void willMovieError() {

    }

    @Override
    public void FindMovieByCinemaIdSuccess(final List<FindMovieByCinemaIdBean.ResultBean> result) {
        Log.i("hhhh", "hotMovieSuccess: " + result);
        MyCoverAdapter myCoverAdapter = new MyCoverAdapter(this, result);
        coverCinemaDetails.setAdapter(myCoverAdapter);
        coverCinemaDetails.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                String id = result.get(position).getId();
                Log.e("idid", "onItemSelected: "+id );
                //((TextView)findViewById(R.id.index)).setText((position+1)+"/"+coverCinemaDetails.getLayoutManager().getItemCount());
                listPresenter.getCinemaDetailsListData(id, cinemaId);
            }
        });
    }

    @Override
    public void FindMovieByCinemaIdError() {

    }
}
