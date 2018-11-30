package com.bw.movie.model.imodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.model.bean.WXBean;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/22
 */
public class WXModel {

    public void getWxLoginData(String code,final wxLoginCallBack wxLoginCallBack){
        Observable<WXBean> wxBeanObservable = HttpUtils.getInstance().api.wxLogin(code);
        wxBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WXBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WXBean wxBean) {
                        wxLoginCallBack.callBack(wxBean);
                        Log.e("wxbean", "onNext: "+wxBean.getResult().getUserInfo().getNickName() );
                    }

                    @Override
                    public void onError(Throwable e) {
                      Log.e("wxbean", "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface wxLoginCallBack{
        void callBack(WXBean wxBean);
    }
}
