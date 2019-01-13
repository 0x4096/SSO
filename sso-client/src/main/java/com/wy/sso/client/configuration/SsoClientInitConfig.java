package com.wy.sso.client.configuration;

import com.wy.sso.core.configuration.SsoClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/5
 * @instructions: 初始化单点登录客户端配置等
 */
@Configuration
public class SsoClientInitConfig {

    @Value("${wy.sso.server.ssoServerUrl}")
    private String ssoServerUrl;

    @Value("${wy.sso.client.appCode}")
    private String appCode;

    @Value("${wy.sso.server.loginUri}")
    private String loginUri;

    @Value("${wy.sso.server.logoutUri}")
    private String logoutUri;

    @Value("${wy.sso.client.includedPaths}")
    private String includedPaths;

    @Value("${wy.sso.client.excludedPaths}")
    private String excludedPaths;

    @Bean
    public SsoClientConfig ssoClientConfig(){
        return new SsoClientConfig(ssoServerUrl,loginUri,logoutUri,appCode,includedPaths,excludedPaths);
    }

}
