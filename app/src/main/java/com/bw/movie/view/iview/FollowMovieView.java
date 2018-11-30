package com.bw.movie.view.iview;

import com.bw.movie.model.bean.FollowMovieBean;

/**
 * @author hyy
 * @date 2018/11/22
 */
public interface FollowMovieView extends BaseView {

    void followMovieSuccess(FollowMovieBean followMovieBean);

    void followMovieError();
}
