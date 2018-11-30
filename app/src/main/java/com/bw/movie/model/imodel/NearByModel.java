package com.bw.movie.model.imodel;

import android.util.Log;

import com.bw.movie.model.bean.NearbyCinemaBean;
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
public class NearByModel  {

    public void getNearByData(final nearbyCallBack nearbyCallBack){
        Observable<NearbyCinemaBean> nearby = HttpUtils.getInstance().api.nearby();
        nearby.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NearbyCinemaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NearbyCinemaBean nearbyCinemaBean) {
                        List<NearbyCinemaBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList = nearbyCinemaBean.getResult().getNearbyCinemaList();
                        Log.i("nnn", "onNext: "+nearbyCinemaList);
                        nearbyCallBack.callBack(nearbyCinemaList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface nearbyCallBack{
        void callBack(List<NearbyCinemaBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList);
    }
}
