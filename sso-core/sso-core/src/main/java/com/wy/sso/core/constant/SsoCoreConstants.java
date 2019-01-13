package com.wy.sso.core.constant;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/3
 * @instructions: sso-core 常量集
 */
public class SsoCoreConstants {

    /**
     * 单点登录服务地址
     */
    public static String SSO_SERVER_URL = "";

    /**
     * 登录请求URI
     */
    public static String LOGIN_URI = "";

    /**
     * 退出登录请求URI
     */
    public static String LOGOUT_URI = "";

    /**
     * 重定向地址
     */
    public static final String REDIRECT_URI = "redirect_uri";


    /**
     * 应用系统唯一代码KEY
     */
    public static final String APP_ID_KEY = "APPCODE";

    /**
     * 应用系统唯一代码VALUE
     */
    public static String APP_ID_VALUE = "";


    /**
     * session_id 用于标记用户登录,Cookie中的一个key
     */
    public static final String SESSION_ID = "WYSESSIONID";

    /**
     * redirect参数
     */
    public static final String REDIRECT_PARAMS = "?" + REDIRECT_URI + "=";

    /**
     * WYSESSION参数
     */
    public static final String SESSIONID_PARAMS = "?" + SESSION_ID + "=";


    public static final String DEFAULT_CHARSER = "UTF-8";



}
