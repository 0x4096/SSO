package com.wy.sso.server.controller;

import com.wy.sso.core.constant.SsoCoreConstants;
import com.wy.sso.core.model.SsoUserBO;
import com.wy.sso.core.util.CookieUtils;
import com.wy.sso.server.bo.SsoUserLoginBO;
import com.wy.sso.server.result.ResponseWrapper;
import com.wy.sso.server.util.JedisUtils;
import com.wy.sso.server.util.SsoUserLoginUtils;
import com.wy.sso.server.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/5
 * @instructions:
 */
@Controller
public class SsoServerController {


    /**
     * 跳转至登录页,输出登录前端HTML等,如果已经登录的情况下访问该链接则跳转redirectURI
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response){

        String redirectUri = request.getRequestURI();

        SsoUserBO ssoUserBO = SsoUserLoginUtils.loginCheck(CookieUtils.getSessionId(request));
        if(ssoUserBO != null){


        }



        return "login.html";
    }


    /**
     * 执行登录操作
     *
     * @param ssoUserLoginBO
     * @return
     */
    @RequestMapping(value = "/doLogin")
    @ResponseBody
    public ResponseWrapper<String> doLogin(@RequestBody SsoUserLoginBO ssoUserLoginBO, HttpServletRequest request){
        ResponseWrapper<String> result = new ResponseWrapper<>();


        try {
            ssoUserLoginBO.setRedirectUri(URLDecoder.decode(ssoUserLoginBO.getRedirectUri(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 1. 验证码判断
         * 2. 用户码密码判断
         * 3. 是否在其他终端登录(包括一台PC多个浏览器登录)
         */

        // TODO 理应是数据库数据
        String userCode = "12345678";


        SsoUserBO ssoUserBO = new SsoUserBO(userCode,ssoUserLoginBO.getUsername());


        //根据用户码和用户名的hashCode作为key,sessionId作为value存入redis,用于检测用户是否多终端登录

        String redisSessionId = JedisUtils.getStringValue("u-" + ssoUserBO.toString().hashCode());
        /* 不为空说明已经在其他终端登录了 */
        if(StringUtils.isNotBlank(redisSessionId)){
            /* 清除登录信息 */
            SsoUserLoginUtils.logout(redisSessionId, ssoUserBO);
        }


        String sessionId = StringUtil.sessionIdGenerate();
        /* 登录操作 */
        SsoUserLoginUtils.login(sessionId,ssoUserBO);

        /* 跳转来时的地址,附上sessionId,redirect地址存在参数情况即xxx?key=value */
        String redirectUrl = ssoUserLoginBO.getRedirectUri();
        if(redirectUrl.indexOf('?') != -1){
            redirectUrl = redirectUrl + "&" + SsoCoreConstants.SESSION_ID + "=" + sessionId;
        }else{
            redirectUrl = redirectUrl + SsoCoreConstants.SESSIONID_PARAMS + sessionId;
        }


        result.setData(redirectUrl);
        return result;
    }


}
