package com.bw.movie.view.iview;

import com.bw.movie.model.bean.UpLoadHeadPicBean;

/**
 * @author hyy
 * @date 2018/11/23
 */
public interface UpLoadHearPic extends BaseView {

    void upLoadHeadPicSuccess(UpLoadHeadPicBean upLoadHeadPicBean);

    void upLoadHeadPicError();
}
