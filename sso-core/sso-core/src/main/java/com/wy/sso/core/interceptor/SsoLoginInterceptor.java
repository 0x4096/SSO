package com.wy.sso.core.interceptor;

import com.wy.sso.core.constant.SsoCoreConstants;
import com.wy.sso.core.facade.RpcResult;
import com.wy.sso.core.facade.SsoServiceFacade;
import com.wy.sso.core.model.SsoUserBO;
import com.wy.sso.core.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/3
 * @instructions: 登录拦截器
 */
public class SsoLoginInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SsoLoginInterceptor.class);

    @Resource
    private SsoServiceFacade ssoServiceFacade;


    public SsoLoginInterceptor(String ssoServerUrl,String appCode){
        SsoCoreConstants.SSO_SERVER_URL = ssoServerUrl;
        SsoCoreConstants.APP_CODE = appCode;
        LOGGER.info("SsoLoginInterceptor init success!");
    }


    /**
     * 在请求处理之前进行调用(Controller方法调用之前)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        /* URL */
        String requestUrl = request.getRequestURL().toString();

        String redirectUrl = request.getParameter(SsoCoreConstants.REDIRECT_URL);

        /* 拦截logout操作 */
        if ( StringUtils.equals(uri, SsoCoreConstants.LOGOUT_URI)  ) {
            /* 删除cookie信息 */
            CookieUtils.removeSessionId(request,response);
            /* 重定向到sso服务中心做登出处理; */
            if(StringUtils.isBlank(redirectUrl)){
                redirectUrl = requestUrl.substring(0, requestUrl.length() - SsoCoreConstants.LOGOUT_URI.length()) ;
            }
            String logoutPageUrl = SsoCoreConstants.SSO_SERVER_URL.concat(SsoCoreConstants.LOGOUT_URI) + SsoCoreConstants.REDIRECT_PARAMS + redirectUrl;
            response.sendRedirect(logoutPageUrl);
            return false;
        }

        /* 登录成功后跳转过来设置cookie,appCode后再跳转到来的时候的页面 */
        String sessionId = request.getParameter(SsoCoreConstants.SESSION_ID);
        /* 说明登录成功后跳转过来的 */
        if( sessionId != null ){
            /* 设置cookie */
            CookieUtils.set(response, SsoCoreConstants.SESSION_ID, sessionId);
            CookieUtils.set(response, SsoCoreConstants.APP_CODE, "");
            /* 重定向到来的时候的页面 */
            response.sendRedirect(requestUrl);
            return false;
        }

        /* 根据cookie中的sessionId查询是否有用户信息,若有则放行 */
        sessionId = CookieUtils.getValue(request, SsoCoreConstants.SESSION_ID);
        if(StringUtils.isBlank(sessionId)){
            response.sendRedirect(SsoCoreConstants.SSO_SERVER_URL + SsoCoreConstants.REDIRECT_PARAMS + requestUrl );
            return false;
        }
        RpcResult<SsoUserBO> rpcResult = ssoServiceFacade.checkLoginBySessionId(sessionId);
        if( ! rpcResult.isSuccess() || rpcResult.getData() == null ){
            response.sendRedirect(SsoCoreConstants.SSO_SERVER_URL + SsoCoreConstants.REDIRECT_PARAMS + requestUrl );
            return false;
        }

        return true;
    }



    /**
     * 请求处理之后进行调用,但是在视图被渲染之前(Controller方法调用之后)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用,也就是在DispatcherServlet 渲染了对应的视图之后执行(主要是用于进行资源清理工作)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }



}
