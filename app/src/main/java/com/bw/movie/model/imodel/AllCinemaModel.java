package com.bw.movie.model.imodel;

import android.util.Log;

import com.bw.movie.model.bean.AllCinemaBean;
import com.bw.movie.model.net.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/8
 */
public class AllCinemaModel {

    public void getAllCinemaData(final allCinemaCallBack allCinemaCallBack){
        Observable<AllCinemaBean> allCinema = HttpUtils.getInstance().api.allCinema();
        allCinema.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllCinemaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AllCinemaBean allCinemaBean) {
                        List<AllCinemaBean.ResultBean> result = allCinemaBean.getResult();
                        Log.i("lll", "onNext: "+result);
                        allCinemaCallBack.callBack(result);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface allCinemaCallBack{
        void callBack(List<AllCinemaBean.ResultBean> result);
    }
}
