package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.model.bean.WXBean;
import com.bw.movie.model.imodel.WXModel;
import com.bw.movie.view.iview.WXView;

/**
 * @author hyy
 * @date 2018/11/22
 */
public class WxPresenter extends BasePresenter<WXView> {

    private final WXModel wxModel;

    public WxPresenter(){
        wxModel = new WXModel();
    }

    public void getWxLoginData(String code){
        wxModel.getWxLoginData(code, new WXModel.wxLoginCallBack() {
            @Override
            public void callBack(WXBean wxBean) {

                Log.e("wxbean",wxBean.getStatus());
                getView().wxLoginSuccess(wxBean);
            }
        });
    }
}
