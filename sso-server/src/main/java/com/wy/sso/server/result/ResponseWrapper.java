package com.wy.sso.server.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/5
 * @instructions: SSO-SERVER 响应信息封装
 */
public class ResponseWrapper<T> implements Serializable {


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


    public ResponseWrapper(){
        this.code = "200";
        this.msg = "success";
        this.success = true;
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

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }
}
