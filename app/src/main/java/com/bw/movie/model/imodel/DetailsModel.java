package com.bw.movie.model.imodel;

import android.util.Log;

import com.bw.movie.model.bean.DetailsBean;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/8
 */
public class DetailsModel {

    public void getDetailsData(String movieId, final detailsCallBack detailsCallBack){
        Observable<DetailsBean> details = HttpUtils.getInstance().api.details(movieId);
        details.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        detailsCallBack.callBack(detailsBean);
                        Log.i("dddd", "onNext: "+detailsBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i("eeee", "onNext: "+"error");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface detailsCallBack{
        void callBack(DetailsBean detailsBean);
    }
}
