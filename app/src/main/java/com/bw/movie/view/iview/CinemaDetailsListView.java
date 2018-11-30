package com.bw.movie.view.iview;

import com.bw.movie.model.bean.CinemaDetailsListBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/16
 */
public interface CinemaDetailsListView extends BaseView {

    void cinemaDetailsListSuccess(List<CinemaDetailsListBean.ResultBean> result);

    void cinemaDetailsListError();
}
