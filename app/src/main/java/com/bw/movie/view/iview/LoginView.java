package com.bw.movie.view.iview;

import com.bw.movie.model.bean.LoginBean;

/**
 * @author hyy
 * @date 2018/11/14
 */
public interface LoginView extends BaseView {

    void loginSuccess(LoginBean loginBean);

    void loginError();
}
