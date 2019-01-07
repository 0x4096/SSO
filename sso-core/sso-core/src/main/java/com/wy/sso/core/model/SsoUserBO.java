package com.wy.sso.core.model;

import java.io.Serializable;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/5
 * @instructions: 用户基本信息
 */
public class SsoUserBO implements Serializable {

    /**
     * 用户唯一ID
     */
    private String userCode;

    /**
     * 用户名
     */
    private String username;

    public SsoUserBO() {
    }

    public SsoUserBO(String userCode, String username) {
        this.userCode = userCode;
        this.username = username;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
