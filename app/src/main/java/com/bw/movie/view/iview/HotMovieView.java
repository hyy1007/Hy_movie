package com.bw.movie.view.iview;

import com.bw.movie.model.bean.HotMovieBean;

/**
 * @author hyy
 * @date 2018/11/3
 */
public interface HotMovieView extends BaseView {

    void hotMovieSuccess(HotMovieBean hotMovieBean);

    void hotMovieError();
}
