package com.bw.movie.view.iview;

import com.bw.movie.model.bean.FindMovieByCinemaIdBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/21
 */
public interface FindMovieByCinemaIdView extends BaseView {

    void FindMovieByCinemaIdSuccess(List<FindMovieByCinemaIdBean.ResultBean> result);

    void FindMovieByCinemaIdError();
}
