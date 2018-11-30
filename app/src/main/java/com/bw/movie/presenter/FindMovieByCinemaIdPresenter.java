package com.bw.movie.presenter;

import com.bw.movie.model.bean.FindMovieByCinemaIdBean;
import com.bw.movie.model.imodel.FindMovieByCinemaIdModel;
import com.bw.movie.view.iview.FindMovieByCinemaIdView;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/21
 */
public class FindMovieByCinemaIdPresenter extends BasePresenter<FindMovieByCinemaIdView> {

    private final FindMovieByCinemaIdModel findMovieByCinemaIdModel;

    public FindMovieByCinemaIdPresenter(){
        findMovieByCinemaIdModel = new FindMovieByCinemaIdModel();
    }

    public void getFindMovieByCinemaIdData(String cinemaId){
        findMovieByCinemaIdModel.getFindMovieByCinemaIdData(cinemaId, new FindMovieByCinemaIdModel.FindMovieByCinemaIdCallBack() {
            @Override
            public void callBack(List<FindMovieByCinemaIdBean.ResultBean> result) {
                getView().FindMovieByCinemaIdSuccess(result);
            }
        });
    }
}
