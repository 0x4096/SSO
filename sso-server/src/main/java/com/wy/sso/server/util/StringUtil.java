package com.wy.sso.server.util;

import java.util.UUID;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/6
 * @instructions:
 */
public class StringUtil {

    public static String sessionIdGenerate(){
        return UUID.randomUUID().toString().replace("-","");
    }


}
