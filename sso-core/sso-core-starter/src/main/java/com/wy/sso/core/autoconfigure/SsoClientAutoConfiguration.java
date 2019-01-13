package com.wy.sso.core.autoconfigure;

import com.wy.sso.core.config.ApplicationConfig;
import com.wy.sso.core.configuration.SsoClientConfig;
import com.wy.sso.core.constant.SsoCoreAutoConfigConstants;
import com.wy.sso.core.facade.SsoServiceFacade;
import com.wy.sso.core.interceptor.SsoLoginInterceptorProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.SortedMap;

import static com.wy.sso.core.util.PropertiesUtil.filterProperties;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/13
 * @instructions: 自定义自动加载
 * matchIfMissing 缺少该property时是否可以加载。如果为true，没有该property也会正常加载；
 * havingValue 可与name组合使用，比较获取到的属性值与havingValue给定的值是否相同，相同才加载配置
 */
@Configuration
@ConditionalOnProperty(prefix = SsoCoreAutoConfigConstants.CONFIG_PREFIX, name = "enable", matchIfMissing = false, havingValue = "true")
public class SsoClientAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(SsoClientAutoConfiguration.class);

    @Autowired
    private ConfigurableEnvironment environment;

    @Resource
    private SsoServiceFacade ssoServiceFacade;


    @Bean
    public ApplicationConfig applicationConfig(){
        SortedMap<String, Object> properties = filterProperties(environment);

        ApplicationConfig applicationConfig = new ApplicationConfig();

        Iterator<SortedMap.Entry<String, Object>> it = properties.entrySet().iterator();
        while(it.hasNext()){
            SortedMap.Entry<String, Object> entry = it.next();

            switch (entry.getKey()){
                case SsoCoreAutoConfigConstants.ENABLE:
                    applicationConfig.setEnable(Boolean.valueOf((String) entry.getValue()));
                    break;
                case SsoCoreAutoConfigConstants.SERVER_URL:
                    applicationConfig.setServerUrl((String) entry.getValue());
                    break;
                case SsoCoreAutoConfigConstants.APPID:
                    applicationConfig.setAppId((String) entry.getValue());
                    break;
                case SsoCoreAutoConfigConstants.LOGIN_URI:
                    applicationConfig.setLoginUri((String) entry.getValue());
                    break;
                case SsoCoreAutoConfigConstants.LOGOUT_URI:
                    applicationConfig.setLogoutUri((String) entry.getValue());
                    break;
                case SsoCoreAutoConfigConstants.INCLUDED_PATHS:
                    if(StringUtils.isEmpty(applicationConfig.getIncludedPaths())){
                        applicationConfig.setIncludedPaths((String)entry.getValue());
                    }else{
                        applicationConfig.getIncludedPaths().concat(entry.getValue()+",");
                    }
                    break;
                case SsoCoreAutoConfigConstants.EXCLUDED_PATHS:
                    if(StringUtils.isEmpty(applicationConfig.getExcludedPaths())){
                        applicationConfig.setExcludedPaths((String) entry.getValue());
                    }else{
                        applicationConfig.getExcludedPaths().concat(entry.getValue()+",");
                    }
                    break;
                default:
                    break;
            }
        }

        if( StringUtils.isBlank(applicationConfig.getAppId())  ){
            LOGGER.error("SSO初始化配置出错,请配置完整的wy.sso.client.appId:{}",applicationConfig.getAppId());
            throw new NullPointerException("SSO初始化配置出错,请配置完整的wy.sso.client.appId");
        }

        if(StringUtils.isBlank(applicationConfig.getServerUrl())){
            LOGGER.error("SSO初始化配置出错,请配置完整的wy.sso.server.serverUrl:{}",applicationConfig.getServerUrl());
            throw new NullPointerException("SSO初始化配置出差,请配置完整的wy.sso.server.serverUrl");
        }

        if(StringUtils.isBlank(applicationConfig.getLoginUri())){
            LOGGER.error("SSO初始化配置出错,请配置完整的wy.sso.server.loginUri:{}",applicationConfig.getLoginUri());
            throw new NullPointerException("SSO初始化配置出错,请配置完整的wy.sso.server.loginUri");
        }

        if(StringUtils.isBlank(applicationConfig.getLogoutUri())){
            LOGGER.error("SSO初始化配置出错,请配置完整的wy.sso.server.logoutUri:{}",applicationConfig.getLogoutUri());
            throw new NullPointerException("SSO初始化配置出错,请配置完整的wy.sso.server.logoutUri");
        }

        return applicationConfig;
    }

    /**
     *
     *
     */
    @Bean
    public SsoClientConfig ssoClientConfig(){
        ApplicationConfig applicationConfig = applicationConfig();
        SsoLoginInterceptorProperties ssoLoginInterceptorProperties = new SsoLoginInterceptorProperties();
        BeanUtils.copyProperties(applicationConfig,ssoLoginInterceptorProperties);
        return new SsoClientConfig(ssoLoginInterceptorProperties,ssoServiceFacade);
    }


}
