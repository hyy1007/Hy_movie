package com.bw.movie.view.iview;

import com.bw.movie.model.bean.ReviewListBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/17
 */
public interface ReviewListView extends BaseView {

    void reviewListSuccess(List<ReviewListBean.ResultBean> result);

    void reviewListError();
}
