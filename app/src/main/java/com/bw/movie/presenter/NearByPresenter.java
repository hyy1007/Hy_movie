package com.bw.movie.presenter;

import com.bw.movie.model.bean.NearbyCinemaBean;
import com.bw.movie.model.imodel.NearByModel;
import com.bw.movie.view.iview.NearByView;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/8
 */
public class NearByPresenter extends BasePresenter<NearByView> {

    private final NearByModel nearByModel;

    public NearByPresenter(){
        nearByModel = new NearByModel();
    }

    public void getNearByData(){
        nearByModel.getNearByData(new NearByModel.nearbyCallBack() {
            @Override
            public void callBack(List<NearbyCinemaBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList) {
                getView().nearbySuccess(nearbyCinemaList);
            }
        });
    }
}
