package com.bw.movie.view.iview;

import com.bw.movie.model.bean.DetailsBean;

/**
 * @author hyy
 * @date 2018/11/8
 */
public interface DetailsView extends BaseView {

    void detailsSuccess(DetailsBean detailsBean);

    void detailsError();
}
