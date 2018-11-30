package com.bw.movie.model.imodel;

import com.bw.movie.model.bean.CinemaDetailsBean;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/15
 */
public class CinemaDetailsModel {

    public void getCinemaDetailsData(String cinemaId,final cinemaDetailsCallBack cinemaDetailsCallBack){
        Observable<CinemaDetailsBean> cinemaDetails = HttpUtils.getInstance().api.cinemaDetails(cinemaId);
        cinemaDetails.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaDetailsBean cinemaDetailsBean) {
                        CinemaDetailsBean.ResultBean result = cinemaDetailsBean.getResult();
                        cinemaDetailsCallBack.callBack(result);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface cinemaDetailsCallBack{
        void callBack(CinemaDetailsBean.ResultBean result);
    }
}
