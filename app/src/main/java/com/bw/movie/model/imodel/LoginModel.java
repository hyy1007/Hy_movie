package com.bw.movie.model.imodel;

import com.bw.movie.model.bean.LoginBean;
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
public class LoginModel  {

    public void getLoginData(Map<String,String> map,final loginCallBack loginCallBack){
        Observable<LoginBean> login = HttpUtils.getInstance().api.login(map);
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        loginCallBack.callBack(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface loginCallBack{
        void callBack(LoginBean loginBean);
    }
}
