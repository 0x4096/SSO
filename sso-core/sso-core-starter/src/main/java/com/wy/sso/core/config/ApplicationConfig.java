package com.wy.sso.core.config;


import org.springframework.stereotype.Component;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/13
 * @instructions: SSO 单点登录配置
 */
@Component
public class ApplicationConfig {

    private Boolean enable;

    private String serverUrl;

    private String appId;

    private String loginUri;

    private String logoutUri;

    private String excludedPaths;

    private String includedPaths;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getLoginUri() {
        return loginUri;
    }

    public void setLoginUri(String loginUri) {
        this.loginUri = loginUri;
    }

    public String getLogoutUri() {
        return logoutUri;
    }

    public void setLogoutUri(String logoutUri) {
        this.logoutUri = logoutUri;
    }

    public String getExcludedPaths() {
        return excludedPaths;
    }

    public void setExcludedPaths(String excludedPaths) {
        this.excludedPaths = excludedPaths;
    }

    public String getIncludedPaths() {
        return includedPaths;
    }

    public void setIncludedPaths(String includedPaths) {
        this.includedPaths = includedPaths;
    }
}
