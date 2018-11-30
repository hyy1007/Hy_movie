package com.bw.movie.presenter;

import com.bw.movie.model.bean.WillMovieBean;
import com.bw.movie.model.imodel.WillMovieModel;
import com.bw.movie.view.iview.WillMovieView;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/3
 */
public class WillMoviePresenter extends BasePresenter<WillMovieView> {

    private final WillMovieModel willMovieModel;

    public WillMoviePresenter(){
        willMovieModel = new WillMovieModel();
    }

    public void getWillMovieData(){
        willMovieModel.getWillMovieData(new WillMovieModel.willMovieCallBack() {
            @Override
            public void callBack(List<WillMovieBean.ResultBean> result) {
                getView().willMovieSuccess(result);
            }
        });
    }
}
