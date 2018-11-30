package com.bw.movie.model.imodel;

import com.bw.movie.model.bean.WillMovieBean;
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
public class WillMovieModel {

    public void getWillMovieData(final willMovieCallBack willMovieCallBack){
        Observable<WillMovieBean> willMovie = HttpUtils.getInstance().api.willMovie();
        willMovie.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WillMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WillMovieBean willMovieBean) {
                        List<WillMovieBean.ResultBean> result = willMovieBean.getResult();
                        willMovieCallBack.callBack(result);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface willMovieCallBack{
        void callBack(List<WillMovieBean.ResultBean> result);
    }
}
