package com.bw.movie.presenter;

import com.bw.movie.model.bean.NowMovieBean;
import com.bw.movie.model.imodel.NowMovieModel;
import com.bw.movie.view.iview.NowMovieView;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/3
 */
public class NowMoviePresenter extends BasePresenter<NowMovieView> {

    private final NowMovieModel nowMovieModel;

    public NowMoviePresenter(){
        nowMovieModel = new NowMovieModel();
    }

    public void getNowMovieData(){
        nowMovieModel.getNowMovieData(new NowMovieModel.nowMovieCallBack() {
            @Override
            public void callBack(List<NowMovieBean.ResultBean> result) {
                getView().nowMovieSuccess(result);
            }
        });
    }
}
