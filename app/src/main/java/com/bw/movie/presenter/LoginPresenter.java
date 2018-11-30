package com.bw.movie.presenter;

import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.imodel.LoginModel;
import com.bw.movie.view.iview.LoginView;

import java.util.Map;

/**
 * @author hyy
 * @date 2018/11/14
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    private final LoginModel loginModel;

    public LoginPresenter(){
        loginModel = new LoginModel();
    }

    public void getLoginData(Map<String,String> map){
        loginModel.getLoginData(map, new LoginModel.loginCallBack() {
            @Override
            public void callBack(LoginBean loginBean) {
                getView().loginSuccess(loginBean);
            }
        });
    }
}
