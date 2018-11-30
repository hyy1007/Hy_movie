package com.bw.movie.model.imodel;

import android.util.Log;

import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/3
 */
public class HotMovieModel {

    public void getHotMovieData(final hotMovieCallBack hotMovieCallBack) {
        Observable<HotMovieBean> hotMovie = HttpUtils.getInstance().api.hotMovie();
        hotMovie.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotMovieBean hotMovieBean) {
                        Log.i("aaa", "onNext: "+hotMovieBean);
                        hotMovieCallBack.callBack(hotMovieBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface hotMovieCallBack {
        void callBack(HotMovieBean hotMovieBean);
    }
}
