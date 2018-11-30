package com.bw.movie.model.bean;

/**
 * @author hyy
 * @date 2018/11/7
 */
public class EventBus_MovieBean {
    private int msg;

    public EventBus_MovieBean(int msg) {
        this.msg = msg;
    }

    public EventBus_MovieBean() {
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }
}
