package com.wy.sso.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/5
 * @instructions: SSO-Server启动入口
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:dubbo-*.xml"})
public class SsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class, args);
    }

}

