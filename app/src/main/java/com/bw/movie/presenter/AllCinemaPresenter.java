package com.bw.movie.presenter;

import com.bw.movie.model.bean.AllCinemaBean;
import com.bw.movie.model.imodel.AllCinemaModel;
import com.bw.movie.view.iview.AllCinemaView;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/8
 */
public class AllCinemaPresenter extends BasePresenter<AllCinemaView> {

    private final AllCinemaModel allCinemaModel;

    public AllCinemaPresenter(){
        allCinemaModel = new AllCinemaModel();
    }

    public void getAllCinemaData(){
        allCinemaModel.getAllCinemaData(new AllCinemaModel.allCinemaCallBack() {
            @Override
            public void callBack(List<AllCinemaBean.ResultBean> result) {
                getView().allCinemaSuccess(result);
            }
        });
    }
}
