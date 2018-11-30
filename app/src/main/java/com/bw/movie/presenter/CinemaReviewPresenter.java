package com.bw.movie.presenter;

import com.bw.movie.model.bean.CinemaReviewBean;
import com.bw.movie.model.imodel.CinemaReviewModel;
import com.bw.movie.view.iview.CinemaReviewView;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/19
 */
public class CinemaReviewPresenter extends BasePresenter<CinemaReviewView> {

    private final CinemaReviewModel cinemaReviewModel;

    public CinemaReviewPresenter(){
        cinemaReviewModel = new CinemaReviewModel();
    }

    public void getCinemaReview(String cinemaId){
        cinemaReviewModel.getCinemaReviewData(cinemaId, new CinemaReviewModel.cinemaReviewCallBack() {
            @Override
            public void callBack(List<CinemaReviewBean.ResultBean> result) {
                getView().cinemaReviewSuccess(result);
            }
        });
    }
}
