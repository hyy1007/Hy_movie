package com.bw.movie.model.imodel;

import com.bw.movie.model.bean.RegisterBean;
import com.bw.movie.model.net.HttpUtils;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/14
 */
public class RegisterModel {

    public void getRegisterData(Map<String,String> map,final registerCallBack registerCallBack){
        final Observable<RegisterBean> register = HttpUtils.getInstance().api.register(map);
        register.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        registerCallBack.callBack(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface registerCallBack {
        void callBack(RegisterBean register);
    }
}
