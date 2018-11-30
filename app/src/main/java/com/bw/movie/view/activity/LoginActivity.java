package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.bw.movie.R;
import com.bw.movie.model.app.App;
import com.bw.movie.model.bean.EventCode;
import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.bean.MessageEvent;
import com.bw.movie.model.bean.WXBean;
import com.bw.movie.model.net.EncryptUtil;
import com.bw.movie.presenter.LoginPresenter;
import com.bw.movie.presenter.WxPresenter;
import com.bw.movie.view.iview.LoginView;
import com.bw.movie.view.iview.WXView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView, WXView {

    @BindView(R.id.ed_login_number)
    EditText edLoginNumber;
    @BindView(R.id.ed_login_password)
    EditText edLoginPassword;
    @BindView(R.id.btn_ying)
    ToggleButton btnYing;
    @BindView(R.id.save_pwd)
    CheckBox savePwd;
    @BindView(R.id.zd_login)
    CheckBox zdLogin;
    @BindView(R.id.btn_regirect_tiao)
    TextView btnRegirectTiao;
    @BindView(R.id.member_activity_login_layout)
    LinearLayout memberActivityLoginLayout;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.weixin_login)
    ImageView weixinLogin;

    private String loginName;
    private String loginPwd;
    private String loencryptPwd;
    private LoginPresenter loginPresenter;

    //密码是否可見
    private boolean isPwdVisible = false;
    private WxPresenter wxPresenter;
    private String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        wxPresenter = new WxPresenter();
        wxPresenter.attachView(this);

        String code = getIntent().getStringExtra("code");
        Toast.makeText(this, code, Toast.LENGTH_SHORT).show();

        if (code != null) {
            wxPresenter.getWxLoginData(code);
        }
        //绑定
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);



    }

    @OnClick({R.id.btn_ying, R.id.btn_regirect_tiao, R.id.btn_login, R.id.weixin_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ying:
                btnYing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            edLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        } else {
                            edLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                    }
                });
                break;
            case R.id.btn_regirect_tiao:
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                HashMap<String, String> loginMap = new HashMap<>();
                loginName = edLoginNumber.getText().toString();
                loginPwd = edLoginPassword.getText().toString();

                if (TextUtils.isEmpty(loginName)) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (TextUtils.isEmpty(loginPwd)) {
                        Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        loencryptPwd = EncryptUtil.encrypt(loginPwd);
                        loginMap.put("phone", loginName);
                        loginMap.put("pwd", loencryptPwd);

                        loginPresenter.getLoginData(loginMap);
                    }
                }
                break;
            case R.id.weixin_login:
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wxb3852e6a6b7d9516";
                //向微信发送请求
                App.mWxApi.sendReq(req);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }

    @Override
    public void loginSuccess(LoginBean loginBean) {
        String status = loginBean.getStatus();
        String message = loginBean.getMessage();
        LoginBean.ResultBean result = loginBean.getResult();
        if (status.equalsIgnoreCase("0000")) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            SharedPreferences user = LoginActivity.this.getSharedPreferences("user", MODE_PRIVATE);

            SharedPreferences.Editor edit = user.edit();
            // boolean isLogin = user.getBoolean("isLogin", true);
            edit.putString("touicon", result.getUserInfo().getHeadPic()).commit();
            edit.putString("username", result.getUserInfo().getNickName()).commit();
            edit.putInt("gender", result.getUserInfo().getSex()).commit();
            edit.putString("userId", result.getUserId()).commit();
            edit.putString("sessionId", result.getSessionId()).commit();
            Log.i("userid", "intercept: userId===" + result.getUserId());
            Log.i("sessionId", "intercept: sessionId===" + result.getSessionId());
            edit.putString("phone", result.getUserInfo().getPhone()).commit();
            edit.putBoolean("isLogin", true).commit();
            EventBus.getDefault().post(new MessageEvent(4));
            finish();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loginError() {

    }


    @Override
    public void wxLoginSuccess(WXBean wxBean) {
        message = wxBean.getMessage();
        String status = wxBean.getStatus();
        if (status.equalsIgnoreCase("0000")) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            SharedPreferences user = LoginActivity.this.getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor edit = user.edit();

            edit.putString("touicon", wxBean.getResult().getUserInfo().getHeadPic());
            edit.putString("username", wxBean.getResult().getUserInfo().getNickName());
            edit.putInt("gender", wxBean.getResult().getUserInfo().getSex());
            edit.putString("userId", wxBean.getResult().getUserId());
            edit.putString("sessionId", wxBean.getResult().getSessionId());
            Log.i("userid", "intercept: userId===" + wxBean.getResult().getUserId());
            Log.i("sessionId", "intercept: sessionId===" + wxBean.getResult().getSessionId());
            edit.putString("phone", wxBean.getResult().getUserInfo().getPhone());
            edit.putBoolean("isLogin", true);
            edit.commit();

            Intent intent = new Intent(this, ShowActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void wxLoginError() {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
