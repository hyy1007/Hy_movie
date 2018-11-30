package com.bw.movie.presenter;

import com.bw.movie.model.bean.RegisterBean;
import com.bw.movie.model.imodel.RegisterModel;
import com.bw.movie.view.iview.RetristeView;

import java.util.Map;

/**
 * @author hyy
 * @date 2018/11/14
 */
public class RegisterPresenter extends BasePresenter<RetristeView> {

    private final RegisterModel registerModel;

    public RegisterPresenter(){
        registerModel = new RegisterModel();
    }

    public void getRegisterData(Map<String,String> map){
        registerModel.getRegisterData(map,new RegisterModel.registerCallBack() {
            @Override
            public void callBack(RegisterBean register) {
                getView().registerSuccess(register);
            }
        });
    }
}
