package com.wy.sso.server.configuration;

import com.wy.sso.server.util.JedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/7
 * @instructions:
 */
@Configuration
public class SsoServerInitConfig {

    @Value("${wy.sso.server.redisAddress}")
    private String redisAddress;


    @Bean
    public void jedisUtils(){
        JedisUtils.init(redisAddress);
    }


}
