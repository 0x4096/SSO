package com.wy.sso.server.controller;

import com.wy.sso.core.constant.SsoCoreConstants;
import com.wy.sso.core.model.SsoUserBO;
import com.wy.sso.server.bo.SsoUserLoginBO;
import com.wy.sso.server.constant.SsoServerConstants;
import com.wy.sso.server.result.ResponseWrapper;
import com.wy.sso.server.util.SsoUserLoginUtils;
import com.wy.sso.server.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/5
 * @instructions:
 */
@Controller
public class SsoServerController {


    /**
     * 跳转至登录页,输出登录前端HTML等
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response){


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
    public ResponseWrapper<String> doLogin(@RequestBody SsoUserLoginBO ssoUserLoginBO){
        ResponseWrapper<String> result = new ResponseWrapper<>();

        /**
         * 1. 验证码判断
         * 2. 用户码密码判断
         */

        // TODO 理应是数据库数据
        String userCode = "12345678";

        String sessionId = StringUtil.sessionIdGenerate();
        SsoUserBO ssoUserBO = new SsoUserBO(userCode,ssoUserLoginBO.getUsername());

        /* 登录操作 */
        SsoUserLoginUtils.login(sessionId,ssoUserBO);

        /* 跳转来时的地址,附上sessionId,redirect地址存在参数情况即xxx?key=value */
        String redirectUrl = ssoUserLoginBO.getRedirectUrl();
        if(redirectUrl.indexOf('?') != -1){
            redirectUrl = redirectUrl + "&" + SsoCoreConstants.SESSION_ID + "=" + sessionId;
        }else{
            redirectUrl = redirectUrl + SsoCoreConstants.SESSIONID_PARAMS + sessionId;
        }


        result.setData(redirectUrl);
        return result;
    }


}
