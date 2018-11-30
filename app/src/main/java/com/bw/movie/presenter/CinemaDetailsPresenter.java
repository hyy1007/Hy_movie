package com.bw.movie.presenter;

import com.bw.movie.model.bean.CinemaDetailsBean;
import com.bw.movie.model.imodel.CinemaDetailsModel;
import com.bw.movie.view.iview.CinemaDetailsView;

/**
 * @author hyy
 * @date 2018/11/15
 */
public class CinemaDetailsPresenter extends BasePresenter<CinemaDetailsView> {

    private final CinemaDetailsModel cinemaDetailsModel;

    public CinemaDetailsPresenter(){
        cinemaDetailsModel = new CinemaDetailsModel();
    }

    public void getCinemaDetailsData(String cinemaId){
        cinemaDetailsModel.getCinemaDetailsData(cinemaId, new CinemaDetailsModel.cinemaDetailsCallBack() {
            @Override
            public void callBack(CinemaDetailsBean.ResultBean result) {
                getView().cinemaDetailsSuccess(result);
            }
        });
    }
}
