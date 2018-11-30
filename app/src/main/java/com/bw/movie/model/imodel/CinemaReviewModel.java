package com.bw.movie.model.imodel;

import com.bw.movie.model.bean.CinemaReviewBean;
import com.bw.movie.model.net.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/19
 */
public class CinemaReviewModel {

    public void getCinemaReviewData(String cinemaId,final cinemaReviewCallBack cinemaReviewCallBack){
        Observable<CinemaReviewBean> cinemaReview = HttpUtils.getInstance().api.cinemaReview(cinemaId);
        cinemaReview.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaReviewBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaReviewBean cinemaReviewBean) {
                        List<CinemaReviewBean.ResultBean> result = cinemaReviewBean.getResult();
                        cinemaReviewCallBack.callBack(result);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface cinemaReviewCallBack {
        void callBack(List<CinemaReviewBean.ResultBean> result);
    }
}
