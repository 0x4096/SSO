package com.wy.sso.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/12
 * @instructions: 测试携带参数的HTTP请求
 */
@RestController
public class TestController {


    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        Map<String,String[]> paramsMap = request.getParameterMap();
        Iterator<Map.Entry<String,String[]>> iterator = paramsMap.entrySet().iterator();
        int i = 0;
        while(iterator.hasNext()){
            Map.Entry<String, String[]> entry = iterator.next();
            if(i == 0){
                for(int j=0,length=entry.getValue().length;j<length;j++){
                    if(j == 0){
                        sb.append( entry.getKey() +"=" + entry.getValue()[j]);
                    }else{
                        sb.append("&" + entry.getKey() + "=" + entry.getValue()[j]);
                    }
                }
            }
        }
        return sb.toString();
    }


}
