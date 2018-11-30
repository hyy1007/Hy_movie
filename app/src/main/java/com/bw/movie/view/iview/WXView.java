package com.bw.movie.view.iview;

import com.bw.movie.model.bean.WXBean;

/**
 * @author hyy
 * @date 2018/11/22
 */
public interface WXView extends BaseView {

    void wxLoginSuccess(WXBean wxBean);

    void wxLoginError();
}
