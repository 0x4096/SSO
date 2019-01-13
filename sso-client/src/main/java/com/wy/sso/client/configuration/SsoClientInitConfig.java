package com.wy.sso.client.configuration;

import com.wy.sso.core.configuration.SsoClientConfig;
import com.wy.sso.core.facade.SsoServiceFacade;
import com.wy.sso.core.interceptor.SsoLoginInterceptorProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/5
 * @instructions: 初始化单点登录客户端配置等,如果引入的是sso-core-starter就不要使用@Configuration
 */
//@Configuration
public class SsoClientInitConfig {

    @Value("${wy.sso.server.serverUrl}")
    private String serverUrl;

    @Value("${wy.sso.client.appId}")
    private String appId;

    @Value("${wy.sso.server.loginUri}")
    private String loginUri;

    @Value("${wy.sso.server.logoutUri}")
    private String logoutUri;

    @Value("${wy.sso.client.enable}")
    private boolean enable;

    @Value("${wy.sso.client.includedPaths}")
    private String includedPaths;

    @Value("${wy.sso.client.excludedPaths}")
    private String excludedPaths;


    @Resource
    private SsoServiceFacade ssoServiceFacade;

    /**
     * 注解@Conditional用法,只有满足条件才会执行,这里的意思是只有wy.sso.client.enable=true时才创建这个bean
     *
     * @return
     */
    @Bean
    @Conditional(value = SsoClientInitCondition.class)
    public SsoClientConfig ssoClientConfig(){
        SsoLoginInterceptorProperties properties = new SsoLoginInterceptorProperties(serverUrl, appId, loginUri, logoutUri, includedPaths, excludedPaths);
        return new SsoClientConfig(properties, ssoServiceFacade);
    }

}
