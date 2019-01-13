package com.wy.sso.core.interceptor;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/13
 * @instructions: 用于SSO自动化配置属性
 */
public class SsoLoginInterceptorProperties {

    private Boolean enable;

    private String serverUrl;

    private String appId;

    private String loginUri;

    private String logoutUri;

    private String excludedPaths;

    private String includedPaths;


    public SsoLoginInterceptorProperties() {
    }

    public SsoLoginInterceptorProperties(String serverUrl, String appId, String loginUri, String logoutUri, String includedPaths, String excludedPaths) {
        this.serverUrl = serverUrl;
        this.appId = appId;
        this.loginUri = loginUri;
        this.logoutUri = logoutUri;
        this.excludedPaths = excludedPaths;
        this.includedPaths = includedPaths;
    }

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
