package com.wy.sso.core.util;

import org.springframework.core.env.*;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/13
 * @instructions: 运行环境工具类
 */
public abstract class EnvironmentUtils {

    /**
     * Extras The properties from {@link ConfigurableEnvironment}
     * @param environment {@link ConfigurableEnvironment}
     * @return Read-only Map
     */
    public static Map<String, Object> extractProperties(ConfigurableEnvironment environment) {
        return Collections.unmodifiableMap(doExtraProperties(environment));
    }

    private static Map<String, Object> doExtraProperties(ConfigurableEnvironment environment) {

        Map<String, Object> properties = new LinkedHashMap<>();

        Map<String, PropertySource<?>> map = doGetPropertySources(environment);

        for (PropertySource<?> source : map.values()) {
            if (source instanceof EnumerablePropertySource) {
                EnumerablePropertySource propertySource = (EnumerablePropertySource) source;
                String[] propertyNames = propertySource.getPropertyNames();
                if (ObjectUtils.isEmpty(propertyNames)) {
                    continue;
                }
                for (String propertyName : propertyNames) {
                    if (!properties.containsKey(propertyName)) {
                        properties.put(propertyName, propertySource.getProperty(propertyName));
                    }
                }
            }
        }
        return properties;
    }

    private static Map<String, PropertySource<?>> doGetPropertySources(ConfigurableEnvironment environment) {
        Map<String, PropertySource<?>> map = new LinkedHashMap<String, PropertySource<?>>();
        MutablePropertySources sources = environment.getPropertySources();
        for (PropertySource<?> source : sources) {
            extract("", map, source);
        }
        return map;
    }

    private static void extract(String root, Map<String, PropertySource<?>> map, PropertySource<?> source) {
        if (source instanceof CompositePropertySource) {
            for (PropertySource<?> nest : ((CompositePropertySource) source).getPropertySources()) {
                extract(source.getName() + ":", map, nest);
            }
        } else {
            map.put(root + source.getName(), source);
        }
    }

}
