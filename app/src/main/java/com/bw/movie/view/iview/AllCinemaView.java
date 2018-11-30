package com.bw.movie.view.iview;

import com.bw.movie.model.bean.AllCinemaBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/8
 */
public interface AllCinemaView extends BaseView {

    void allCinemaSuccess(List<AllCinemaBean.ResultBean> result);

    void allCinemaError();
}
