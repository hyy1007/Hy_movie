package com.bw.movie.model.imodel;

import android.util.Log;

import com.bw.movie.model.bean.FollowMovieListBean;
import com.bw.movie.model.net.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/22
 */
public class FollowMovieListModel {


    public void getFollowMovieListData(String userId,String sessionId,final followMovieListCallBack followMovieListCallBack){
        Observable<FollowMovieListBean> followMovieList = HttpUtils.getInstance().api.followMovieList(userId, sessionId);
        followMovieList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowMovieListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowMovieListBean followMovieListBean) {
                        List<FollowMovieListBean.ResultBean> result = followMovieListBean.getResult();
                        Log.e("follow1", "onNext: "+result );
                        followMovieListCallBack.callBack(result);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface followMovieListCallBack{
        void callBack(List<FollowMovieListBean.ResultBean> result);
    }
}
