package com.bw.movie.view.iview;

import com.bw.movie.model.bean.WillMovieBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/3
 */
public interface WillMovieView extends BaseView {

    void willMovieSuccess(List<WillMovieBean.ResultBean> result);

    void willMovieError();
}
