package com.wy.sso.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/5
 * @instructions: Init
 */
@RestController
public class InitController {

    @RequestMapping(value = "")
    public String init(){
        return "hi~";
    }

}
