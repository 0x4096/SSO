package com.wy.sso.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;


/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/13
 * @instructions: 启动时加载此类,用于打印相关启动信息
 */
@Order(LoggingApplicationListener.DEFAULT_ORDER + 1)
public class WelcomeLogoApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {

        final Logger logger = LoggerFactory.getLogger(getClass());
        ConfigurableEnvironment environment = event.getEnvironment();
        String bannerText = buildBannerText();
        if (logger.isInfoEnabled()) {
            logger.info(bannerText);
        } else {
            System.out.print(bannerText);
        }

    }


    /**
     * 欢迎日志信息
     *
     * @return
     */
    String buildBannerText() {

        StringBuilder bannerTextBuilder = new StringBuilder();

        bannerTextBuilder
                .append("--WY-SSO-AUTOCONFIG-INIT-SUCCESS--");

        return bannerTextBuilder.toString();
    }


}
