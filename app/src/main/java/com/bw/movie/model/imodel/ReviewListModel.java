package com.bw.movie.model.imodel;

import android.util.Log;

import com.bw.movie.model.bean.ReviewListBean;
import com.bw.movie.model.net.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/17
 */
public class ReviewListModel {

    public void getReviewListData(String movieId,final reviewListCallBack reviewListCallBack){
        Observable<ReviewListBean> reviewList = HttpUtils.getInstance().api.reviewList(movieId);
        reviewList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReviewListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReviewListBean reviewListBean) {
                        List<ReviewListBean.ResultBean> result = reviewListBean.getResult();
                        Log.i("review1", "onNext: "+result);
                        reviewListCallBack.callBack(result);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface reviewListCallBack{
        void callBack(List<ReviewListBean.ResultBean> result);
    }
}
