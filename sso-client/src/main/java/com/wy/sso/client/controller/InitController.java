package com.wy.sso.client.controller;

import com.wy.sso.core.facade.SsoServiceFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/7
 * @instructions: Init
 */
@RestController
public class InitController {


    @Resource
    private SsoServiceFacade ssoServiceFacade;

    @RequestMapping(value = "")
    public String init(){
        return "hi~";
    }


}
