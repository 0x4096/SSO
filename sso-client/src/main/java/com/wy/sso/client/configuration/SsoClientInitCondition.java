package com.wy.sso.client.configuration;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/13
 * @instructions: 注解@Conditional用法,只有满足条件才会执行,这里的意思是只有wy.sso.client.enable=true时才创建这个bean.
 * 使用注解@Conditional 需要创建一个类实现 Condition 接口
 */
public class SsoClientInitCondition implements Condition {


    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        boolean enable = Boolean.parseBoolean(conditionContext.getEnvironment().getProperty("wy.sso.client.enable"));
        if( enable ){
            return true;
        }
        return false;
    }
}
