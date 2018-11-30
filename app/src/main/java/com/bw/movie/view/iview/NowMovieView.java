package com.bw.movie.view.iview;

import com.bw.movie.model.bean.NowMovieBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/3
 */
public interface NowMovieView extends BaseView {

    void nowMovieSuccess(List<NowMovieBean.ResultBean> result);

    void nowMovieError();
}
