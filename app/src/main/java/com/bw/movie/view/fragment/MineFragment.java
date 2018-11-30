package com.bw.movie.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.bw.movie.R;
import com.bw.movie.view.activity.LikeActivity;
import com.bw.movie.view.activity.LoginActivity;
import com.bw.movie.view.activity.MineInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author hyy
 * @date 2018/11/3
 */
public class MineFragment extends Fragment {
    @BindView(R.id.ming_remind)
    ImageView mingRemind;
    @BindView(R.id.simp_mine_head)
    SimpleDraweeView simpMineHead;
    @BindView(R.id.text_login_regist)
    TextView textLoginRegist;
    @BindView(R.id.btn_msg_mine)
    LinearLayout btnMsgMine;
    @BindView(R.id.btn_like_mine)
    LinearLayout btnLikeMine;
    @BindView(R.id.btn_rccord_mine)
    LinearLayout btnRccordMine;
    @BindView(R.id.btn_feedback_mine)
    LinearLayout btnFeedbackMine;
    @BindView(R.id.btn_version_mine)
    LinearLayout btnVersionMine;
    Unbinder unbinder;
    private SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.text_login_regist, R.id.btn_msg_mine, R.id.btn_like_mine, R.id.btn_rccord_mine, R.id.btn_feedback_mine, R.id.btn_version_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_login_regist:
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_msg_mine:
                Intent intent2 = new Intent(getActivity(), MineInfoActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_like_mine:
                Intent intent3 = new Intent(getActivity(), LikeActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_rccord_mine:
                break;
            case R.id.btn_feedback_mine:
                break;
            case R.id.btn_version_mine:
                break;
        }
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            simpMineHead.setImageBitmap(bitmap);
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            simpMineHead.setImageURI(uri);
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();
        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        if (isLogin){
            String touicon = sp.getString("touicon", "");
            String username = sp.getString("username", "");
            simpMineHead.setImageURI(Uri.parse(touicon));
            textLoginRegist.setText(username);
        }
    }

}
