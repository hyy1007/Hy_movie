package com.bw.movie.wxapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.model.app.App;
import com.bw.movie.view.activity.LoginActivity;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.bw.movie.model.bean.EventCode;

import org.greenrobot.eventbus.EventBus;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private static final int RETURN_MSG_TYPE_LOGIN = 1; //登录
    private static final int RETURN_MSG_TYPE_SHARE = 2; //分享

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果没回调onResp，八成是这句没有写
        App.mWxApi.handleIntent(getIntent(),this);

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
    //请求回调结果处理
    @Override
    public void onResp(BaseResp baseResp) {
        int type = baseResp.getType();
        if(type==RETURN_MSG_TYPE_LOGIN){
            //登录回调
            SendAuth.Resp resp = (SendAuth.Resp) baseResp;
            switch (resp.errCode){
                case BaseResp.ErrCode.ERR_OK:
                    String code = resp.code;
                   startActivity(new Intent(this,LoginActivity.class).putExtra("code",code));
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    break;
            }
        }
    }

}
