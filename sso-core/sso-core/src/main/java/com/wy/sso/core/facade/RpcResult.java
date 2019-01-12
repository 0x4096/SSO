package com.wy.sso.core.facade;

import java.io.Serializable;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/5
 * @instructions: RPC调用结果封装  判断用户是否登录,通过RPC服务进行判断
 */
public class RpcResult<T> implements Serializable {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 返回结果状态码
     */
    private String code;

    /**
     * 返回具体消息详情
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;


    public RpcResult() {
        this.success = true;
        this.code = "200";
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
