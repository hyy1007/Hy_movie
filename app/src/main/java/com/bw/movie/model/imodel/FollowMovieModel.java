package com.bw.movie.model.imodel;

import com.bw.movie.model.bean.FollowMovieBean;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/22
 */
public class FollowMovieModel {

    public void getFollowMovieData(String userId,String sessionId,String movieId,final followMovieCallBack followMovieCallBack){
        Observable<FollowMovieBean> followMovie = HttpUtils.getInstance().api.followMovie(userId, sessionId, movieId);
        followMovie.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowMovieBean followMovieBean) {
                        followMovieCallBack.callBack(followMovieBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface followMovieCallBack {
        void callBack(FollowMovieBean followMovieBean);
    }
}
