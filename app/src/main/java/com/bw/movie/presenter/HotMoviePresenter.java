package com.bw.movie.presenter;

import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.model.imodel.HotMovieModel;
import com.bw.movie.view.iview.HotMovieView;

/**
 * @author hyy
 * @date 2018/11/3
 */
public class HotMoviePresenter extends BasePresenter<HotMovieView> {

    private final HotMovieModel hotMovieModel;

    public HotMoviePresenter(){
        hotMovieModel = new HotMovieModel();
    }

    public void getHotMovieData(){
        hotMovieModel.getHotMovieData(new HotMovieModel.hotMovieCallBack() {
            @Override
            public void callBack(HotMovieBean hotMovieBean) {
                getView().hotMovieSuccess(hotMovieBean);
            }
        });
    }
}
