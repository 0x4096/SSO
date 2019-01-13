package com.wy.sso.core.util;

import com.wy.sso.core.constant.SsoCoreConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/3
 * @instructions: cookie操作工具集
 */
public class CookieUtils {

    /**
     * cookie过期时间 一个月, 未使用
     */
    private static final int COOKIE_MAX_AGE = 60*60*24*30;

    /**
     * 保存路径, 网站根路径
     */
    private static final String COOKIE_PATH = "/";

    /**
     * 保存
     *
     * @param response
     * @param key
     * @param value
     */
    public static void set(HttpServletResponse response, String key, String value) {
        set(response, key, value, null, COOKIE_PATH, true);
    }

    /**
     * 保存
     *
     * @param response
     * @param key
     * @param value
     */
    private static void set(HttpServletResponse response, String key, String value, String domain, String path, boolean isHttpOnly) {
        Cookie cookie = new Cookie(key, value);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setHttpOnly(isHttpOnly);
        response.addCookie(cookie);
    }

    /**
     * 查询value
     *
     * @param request
     * @param key
     * @return
     */
    public static String getValue(HttpServletRequest request, String key) {
        Cookie cookie = get(request, key);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 查询Cookie中指定key的value
     *
     * @param request
     * @param key
     */
    private static Cookie get(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 删除Cookie中指定的key的value
     *
     * @param request
     * @param response
     * @param key
     */
    public static void remove(HttpServletRequest request, HttpServletResponse response, String key) {
        Cookie cookie = get(request, key);
        if (cookie != null) {
            set(response, key, "", null, COOKIE_PATH, true);
        }
    }

    /**
     * 移除cookie中的sessionId信息
     *
     * @param request
     * @param response
     */
    public static void removeSessionId(HttpServletRequest request, HttpServletResponse response){
        remove(request,response, SsoCoreConstants.SESSION_ID);
    }

    /**
     * 获取当前应用系统的AppId
     * @param request
     */
    public static String getAppId(HttpServletRequest request){
        return getValue(request, SsoCoreConstants.APP_CODE_KEY);
    }

    /**
     * 获取sessionId
     *
     * @param request
     * @return
     */
    public static String getSessionId(HttpServletRequest request){
        return getValue(request, SsoCoreConstants.SESSION_ID);
    }


}
