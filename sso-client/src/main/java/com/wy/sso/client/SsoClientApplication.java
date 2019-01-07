package com.wy.sso.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/5
 * @instructions: client-one 启动入口
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:dubbo-*.xml"})
public class SsoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoClientApplication.class, args);
    }

}

