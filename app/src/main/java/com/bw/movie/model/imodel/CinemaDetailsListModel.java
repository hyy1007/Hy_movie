package com.bw.movie.model.imodel;

import android.util.Log;

import com.bw.movie.model.bean.CinemaDetailsListBean;
import com.bw.movie.model.net.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/16
 */
public class CinemaDetailsListModel {


    public void getCinemaDetailsListData(String cinemaId,String movieId,final cinemaDetailsListCallBack cinemaDetailsListCallBack){
        final Observable<CinemaDetailsListBean> cinemaDetailsList = HttpUtils.getInstance().api.cinemaDetailsList(cinemaId, movieId);
        cinemaDetailsList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaDetailsListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaDetailsListBean cinemaDetailsListBean) {
                        List<CinemaDetailsListBean.ResultBean> result = cinemaDetailsListBean.getResult();
                        Log.i("cdlist", "onNext: "+result);
                        cinemaDetailsListCallBack.callBack(result);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface cinemaDetailsListCallBack{
        void callBack(List<CinemaDetailsListBean.ResultBean> result);
    }
}
