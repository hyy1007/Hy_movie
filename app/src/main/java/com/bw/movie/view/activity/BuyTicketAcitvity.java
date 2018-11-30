package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.view.custum.SeatTable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyTicketAcitvity extends AppCompatActivity {


    @BindView(R.id.seatView)
    SeatTable seatView;
    @BindView(R.id.totalprice)
    TextView totalprice;
    @BindView(R.id.img_configpayticket)
    ImageView imgConfigpayticket;
    @BindView(R.id.img_cancelpayticket)
    ImageView imgCancelpayticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket_acitvity);
        ButterKnife.bind(this);
    }
}
