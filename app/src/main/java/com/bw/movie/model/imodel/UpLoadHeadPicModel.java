package com.bw.movie.model.imodel;

import android.util.Log;

import com.bw.movie.model.bean.UpLoadHeadPicBean;
import com.bw.movie.model.net.HttpUtils;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyy
 * @date 2018/11/23
 */
public class UpLoadHeadPicModel {

    public void getUpLoadHeadPicData(String userId,String sessionId,File image,final upLoadHeadPicCallBack upLoadHeadPicCallBack){
        Observable<UpLoadHeadPicBean> loadHeadPic = HttpUtils.getInstance().api.upLoadHeadPic(userId,sessionId,image);
        loadHeadPic.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpLoadHeadPicBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpLoadHeadPicBean upLoadHeadPicBean) {
                        upLoadHeadPicCallBack.callBack(upLoadHeadPicBean);

                        Log.e("headpic", "upLoadHeadPicSuccess: "+upLoadHeadPicBean.getHeadPath() );
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface upLoadHeadPicCallBack{
        void callBack(UpLoadHeadPicBean upLoadHeadPicBean);
    }
}
