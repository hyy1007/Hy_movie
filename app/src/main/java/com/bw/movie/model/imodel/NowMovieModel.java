package com.bw.movie.model.imodel;

import com.bw.movie.model.bean.NowMovieBean;
import com.bw.movie.model.net.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/3
 */
public class NowMovieModel {

    public void getNowMovieData(final nowMovieCallBack nowMovieCallBack) {
        Observable<NowMovieBean> nowMovie = HttpUtils.getInstance().api.nowMovie();
        nowMovie.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NowMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NowMovieBean nowMovieBean) {
                        List<NowMovieBean.ResultBean> result = nowMovieBean.getResult();
                        nowMovieCallBack.callBack(result);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface nowMovieCallBack {
        void callBack(List<NowMovieBean.ResultBean> result);
    }
}
