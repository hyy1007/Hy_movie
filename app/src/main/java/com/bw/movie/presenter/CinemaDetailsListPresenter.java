package com.bw.movie.presenter;

import com.bw.movie.model.bean.CinemaDetailsListBean;
import com.bw.movie.model.imodel.CinemaDetailsListModel;
import com.bw.movie.view.iview.CinemaDetailsListView;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/16
 */
public class CinemaDetailsListPresenter extends BasePresenter<CinemaDetailsListView> {

    private final CinemaDetailsListModel cinemaDetailsListModel;

    public CinemaDetailsListPresenter(){
        cinemaDetailsListModel = new CinemaDetailsListModel();
    }

    public void getCinemaDetailsListData(String cinemaId,String movieId){
        cinemaDetailsListModel.getCinemaDetailsListData(cinemaId, movieId, new CinemaDetailsListModel.cinemaDetailsListCallBack() {
            @Override
            public void callBack(List<CinemaDetailsListBean.ResultBean> result) {
                getView().cinemaDetailsListSuccess(result);
            }
        });
    }
}
