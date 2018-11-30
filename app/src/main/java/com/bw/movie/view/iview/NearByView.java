package com.bw.movie.view.iview;

import com.bw.movie.model.bean.NearbyCinemaBean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/8
 */
public interface NearByView extends BaseView {

    void nearbySuccess(List<NearbyCinemaBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList);

    void nearbyError();
}
