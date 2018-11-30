package com.bw.movie.view.iview;

import com.bw.movie.model.bean.RegisterBean;

/**
 * @author hyy
 * @date 2018/11/14
 */
public interface RetristeView extends BaseView {

    void registerSuccess(RegisterBean register);

    void registerError();
}
