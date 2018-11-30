package com.bw.movie.presenter;

import com.bw.movie.model.bean.UpLoadHeadPicBean;
import com.bw.movie.model.imodel.UpLoadHeadPicModel;
import com.bw.movie.view.iview.UpLoadHearPic;

import java.io.File;

/**
 * @author hyy
 * @date 2018/11/23
 */
public class UpLoadHeadPicPresenter extends BasePresenter<UpLoadHearPic> {

    private final UpLoadHeadPicModel upLoadHeadPicModel;

    public UpLoadHeadPicPresenter(){
        upLoadHeadPicModel = new UpLoadHeadPicModel();
    }

    public void getUpLoadHearPic(String userId,String sessionId,final File image){
        upLoadHeadPicModel.getUpLoadHeadPicData(userId,sessionId,image, new UpLoadHeadPicModel.upLoadHeadPicCallBack() {
            @Override
            public void callBack(UpLoadHeadPicBean upLoadHeadPicBean) {
                getView().upLoadHeadPicSuccess(upLoadHeadPicBean);
            }
        });
    }
}
