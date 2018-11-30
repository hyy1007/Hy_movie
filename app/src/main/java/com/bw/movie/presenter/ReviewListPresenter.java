package com.bw.movie.presenter;

import com.bw.movie.model.bean.ReviewListBean;
import com.bw.movie.model.imodel.ReviewListModel;
import com.bw.movie.view.iview.ReviewListView;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/17
 */
public class ReviewListPresenter extends BasePresenter<ReviewListView> {

    private final ReviewListModel reviewListModel;

    public ReviewListPresenter(){
        reviewListModel = new ReviewListModel();
    }

    public void getReviewListData(String movieId){
        reviewListModel.getReviewListData(movieId, new ReviewListModel.reviewListCallBack() {
            @Override
            public void callBack(List<ReviewListBean.ResultBean> result) {
                getView().reviewListSuccess(result);
            }
        });
    }
}
