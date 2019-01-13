package com.wy.sso.core.configuration;

import com.wy.sso.core.facade.SsoServiceFacade;
import com.wy.sso.core.interceptor.SsoLoginInterceptor;
import com.wy.sso.core.interceptor.SsoLoginInterceptorProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/6
 * @instructions: 需要被初始化单点登录客户端的配置
 */
public class SsoClientConfig implements WebMvcConfigurer {

    /**
     * 单点登录登录服务端地址
     */
    private String serverUrl;

    /**
     * 登录操作URI
     */
    private String loginUri;

    /**
     * 登出操作URI
     */
    private String logoutUri;

    /**
     * 应用系统ID
     */
    private String appId;

    /**
     * 拦截器拦截路径
     */
    private String includedPaths;

    /**
     * 拦截器放行地址
     */
    private String excludedPaths;

    private SsoServiceFacade ssoServiceFacade;

    public SsoClientConfig(SsoLoginInterceptorProperties properties, SsoServiceFacade ssoServiceFacade) {
        this.serverUrl = properties.getServerUrl();
        this.loginUri = properties.getLoginUri();
        this.logoutUri = properties.getLogoutUri();
        this.appId = properties.getAppId();
        this.includedPaths = properties.getIncludedPaths();
        this.excludedPaths = properties.getExcludedPaths();
        this.ssoServiceFacade = ssoServiceFacade;
    }





    /**
     * 登录拦截器
     * @return
     */
    @Bean("ssoLoginInterceptor")
    public SsoLoginInterceptor ssoLoginInterceptor(){
        return new SsoLoginInterceptor(serverUrl, loginUri, logoutUri, appId, ssoServiceFacade);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /* 拦截的请求路径 */
        String []addPathPatternsArrays;
        if(StringUtils.isBlank(includedPaths)){
            addPathPatternsArrays = new String[]{"/**"};
        }else{
            addPathPatternsArrays = includedPaths.split(",");
        }

        /* 放行的请求路径 */
        String []excludePathPatternsArrays = excludedPaths.split(",");

        registry.addInterceptor(ssoLoginInterceptor())
                .order(Integer.MIN_VALUE)
                .addPathPatterns(addPathPatternsArrays)
                .excludePathPatterns(excludePathPatternsArrays);
    }


}
