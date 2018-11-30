package com.bw.movie.view.iview;

import com.bw.movie.model.bean.CinemaDetailsBean;

/**
 * @author hyy
 * @date 2018/11/15
 */
public interface CinemaDetailsView extends BaseView {

    void cinemaDetailsSuccess(CinemaDetailsBean.ResultBean result);

    void cinemaDetailsError();
}
