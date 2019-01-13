package com.wy.sso.server.util;

import com.wy.sso.core.model.SsoUserBO;
import com.wy.sso.server.constant.SsoServerConstants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/6
 * @instructions: 用户登录,检验登录等工具集 参考代码:https://github.com/xuxueli/xxl-sso/raw/master/xxl-sso-core/src/main/java/com/xxl/sso/core/login/SsoTokenLoginHelper.java
 */
public class SsoUserLoginUtils {


    /**
     * client login
     *
     * @param sessionId
     * @param ssoUserBO
     */
    public static void login(String sessionId, SsoUserBO ssoUserBO) {
        if(StringUtils.isBlank(sessionId)){
            throw new NullPointerException("sessionId 不能为空!");
        }
        /* 存入登录用户信息 */
        JedisUtils.setObjectValue(sessionId,ssoUserBO,SsoServerConstants.EXPIRE_TIME);
        /* 根据用户码和用户名的hashCode作为key,sessionId作为value存入redis,用于检测用户是否多终端登录 */
        JedisUtils.setStringValue("u-" + ssoUserBO.toString().hashCode(),sessionId,SsoServerConstants.EXPIRE_TIME);
    }

    /**
     * client logout
     *
     * @param sessionId
     * @param ssoUserBO
     */
    public static void logout(String sessionId, SsoUserBO ssoUserBO) {
        if(StringUtils.isBlank(sessionId)){
            return;
        }
        JedisUtils.del(sessionId);
        JedisUtils.del( "u-" + ssoUserBO.toString().hashCode() );
    }


    /**
     * login check
     *
     * @param sessionId
     * @return
     */
    public static SsoUserBO loginCheck(String  sessionId){
        if(StringUtils.isBlank(sessionId)){
            return null;
        }
        SsoUserBO ssoUserBO = (SsoUserBO) JedisUtils.getObjectValue(sessionId);
        if(ssoUserBO != null){
            /* 延迟数据过期时间 */
            JedisUtils.expire(sessionId, SsoServerConstants.EXPIRE_TIME);
            return ssoUserBO;
        }
        return null;
    }


}
