package com.bw.movie.presenter;

import com.bw.movie.model.bean.DetailsBean;
import com.bw.movie.model.imodel.DetailsModel;
import com.bw.movie.view.iview.DetailsView;

/**
 * @author hyy
 * @date 2018/11/8
 */
public class DetailsPresenter extends BasePresenter<DetailsView> {

    private final DetailsModel detailsModel;
    public DetailsPresenter(){
        detailsModel = new DetailsModel();
    }

    public void getDetailsData(final String movieId){
        detailsModel.getDetailsData(movieId,new DetailsModel.detailsCallBack() {
            @Override
            public void callBack(DetailsBean detailsBean) {
                getView().detailsSuccess(detailsBean);
            }
        });
    }
}
