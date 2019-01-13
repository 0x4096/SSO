package com.wy.sso.core.util;

import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/13
 * @instructions: 配置文件工具类
 */
public class PropertiesUtil {

    /**
     * 过滤非 wy.sso.* 配置
     * @param environment
     * @return
     */
    public static SortedMap<String, Object> filterProperties(ConfigurableEnvironment environment) {
        SortedMap<String, Object> ssoProperties = new TreeMap<>();
        Map<String, Object> properties = EnvironmentUtils.extractProperties(environment);
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String propertyName = entry.getKey();
            if (propertyName.startsWith("wy.sso" + ".") && entry.getValue() != null) {
                ssoProperties.put(propertyName, entry.getValue().toString());
            }
        }
        return Collections.unmodifiableSortedMap(ssoProperties);
    }
}
