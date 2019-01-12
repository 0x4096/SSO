package com.wy.sso.core.configuration;

import com.wy.sso.core.facade.SsoServiceFacade;
import com.wy.sso.core.interceptor.SsoLoginInterceptor;
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
    private String ssoServerUrl;

    /**
     * 登录操作URI
     */
    private String loginUri;

    /**
     * 登出操作URI
     */
    private String logoutUri;

    /**
     * 应用系统码
     */
    private String appCode;

    /**
     * 拦截器拦截路径
     */
    private String includedPaths;

    /**
     * 拦截器放行地址
     */
    private String excludedPaths;


    @Resource
    private SsoServiceFacade ssoServiceFacade;

    public SsoClientConfig(String ssoServerUrl, String loginUri, String logoutUri, String appCode, String includedPaths, String excludedPaths) {
        this.ssoServerUrl = ssoServerUrl;
        this.loginUri = loginUri;
        this.logoutUri = logoutUri;
        this.appCode = appCode;
        this.includedPaths = includedPaths;
        this.excludedPaths = excludedPaths;
    }





    /**
     * 登录拦截器
     * @return
     */
    @Bean("ssoLoginInterceptor")
    public SsoLoginInterceptor ssoLoginInterceptor(){
        return new SsoLoginInterceptor(ssoServerUrl, loginUri, logoutUri, appCode, ssoServiceFacade);
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
