package com.takeaway.until;

import java.io.Serializable;

/**
 * @author kafka
 *封装响应结果类
 */
public class DataRes<E> implements Serializable {
    /**状态码**/
    private Integer state;
    /**状态描述信息**/
    private String message;
    /**响应数据***/
    private E data;

    public DataRes() {
        super();
    }

    public DataRes(Integer state) {
        super();
        this.state = state;
    }

    public DataRes(String message) {
        super();
        this.message = message;
    }

    public DataRes(E data) {
        super();
        this.data = data;
    }

    public DataRes(String message, E data) {
        super();
        this.message = message;
        this.data = data;
    }

    public DataRes(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public DataRes(Integer state, String message, E data) {
        super();
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
