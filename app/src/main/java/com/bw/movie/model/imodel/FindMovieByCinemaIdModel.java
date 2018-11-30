package com.bw.movie.model.imodel;

import com.bw.movie.model.bean.FindMovieByCinemaIdBean;
import com.bw.movie.model.net.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/21
 */
public class FindMovieByCinemaIdModel {

    public void getFindMovieByCinemaIdData(String cinemaId,final FindMovieByCinemaIdCallBack findMovieByCinemaIdCallBack){
        Observable<FindMovieByCinemaIdBean> movieByCinemaId = HttpUtils.getInstance().api.findMovieByCinemaId(cinemaId);
        movieByCinemaId.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindMovieByCinemaIdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindMovieByCinemaIdBean findMovieByCinemaIdBean) {
                        List<FindMovieByCinemaIdBean.ResultBean> result = findMovieByCinemaIdBean.getResult();
                        findMovieByCinemaIdCallBack.callBack(result);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface FindMovieByCinemaIdCallBack{
        void callBack(List<FindMovieByCinemaIdBean.ResultBean> result);
    }
}
