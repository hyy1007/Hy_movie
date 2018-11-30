package com.bw.movie.view.iview;

import com.bw.movie.model.bean.CinemaReviewBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/19
 */
public interface CinemaReviewView extends BaseView {

    void cinemaReviewSuccess(List<CinemaReviewBean.ResultBean> result);

    void cinemaReviewError();
}
