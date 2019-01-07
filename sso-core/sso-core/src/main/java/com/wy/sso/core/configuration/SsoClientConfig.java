package com.wy.sso.core.configuration;

import com.wy.sso.core.interceptor.SsoLoginInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    public SsoClientConfig(String ssoServerUrl, String appCode, String includedPaths, String excludedPaths) {
        this.ssoServerUrl = ssoServerUrl;
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
        return new SsoLoginInterceptor(ssoServerUrl,appCode);
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
