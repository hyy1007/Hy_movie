package com.bw.movie.presenter;

import com.bw.movie.model.bean.FollowMovieBean;
import com.bw.movie.model.imodel.FollowMovieModel;
import com.bw.movie.view.iview.FollowMovieView;

/**
 * @author hyy
 * @date 2018/11/22
 */
public class FollowMoviePresenter extends BasePresenter<FollowMovieView> {

    private final FollowMovieModel followMovieModel;

    public FollowMoviePresenter(){
        followMovieModel = new FollowMovieModel();
    }

    public void getFollowMovieBean(String userId,String sessionId,String movieId){
        followMovieModel.getFollowMovieData(userId, sessionId, movieId, new FollowMovieModel.followMovieCallBack() {
            @Override
            public void callBack(FollowMovieBean followMovieBean) {
                getView().followMovieSuccess(followMovieBean);
            }
        });
    }
}
