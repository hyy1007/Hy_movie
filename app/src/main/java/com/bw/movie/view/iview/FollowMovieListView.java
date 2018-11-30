package com.bw.movie.view.iview;

import com.bw.movie.model.bean.FollowMovieListBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/22
 */
public interface FollowMovieListView extends BaseView {

    void followMovieListSuccess(List<FollowMovieListBean.ResultBean> result);

    void followMovieListError();
}
