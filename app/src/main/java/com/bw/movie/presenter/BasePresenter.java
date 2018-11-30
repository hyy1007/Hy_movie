package com.bw.movie.presenter;

import com.bw.movie.view.iview.BaseView;

/**
 * @author hyy
 * @date 2018/11/3
 */
public class BasePresenter<V extends BaseView> {

    public V v;

    public void attachView(V iv){
        this.v=iv;
    }

    public void detachView(){
        this.v=null;
    }

    public V getView(){
        return this.v;
    }
}
