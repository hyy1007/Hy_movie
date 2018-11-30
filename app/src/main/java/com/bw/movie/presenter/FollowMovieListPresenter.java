package com.bw.movie.presenter;

import com.bw.movie.model.bean.FollowMovieListBean;
import com.bw.movie.model.imodel.FollowMovieListModel;
import com.bw.movie.view.iview.FollowMovieListView;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/22
 */
public class FollowMovieListPresenter extends BasePresenter<FollowMovieListView> {

    private FollowMovieListModel followMovieListModel;
    public FollowMovieListPresenter(){
        followMovieListModel = new FollowMovieListModel();
    }

    public void getFollowMovieListData(String userId,String sessionId){
        followMovieListModel.getFollowMovieListData(userId, sessionId, new FollowMovieListModel.followMovieListCallBack() {
            @Override
            public void callBack(List<FollowMovieListBean.ResultBean> result) {
                getView().followMovieListSuccess(result);
            }
        });
    }
}
