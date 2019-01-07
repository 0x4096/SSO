package com.wy.sso.core.facade;

import com.wy.sso.core.model.SsoUserBO;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/5
 * @instructions: 定义RPC服务接口,引用单点登录的应用系统需引用该接口的dubbo服务实现(配置消费者)
 */
public interface SsoServiceFacade {

    /**
     * 通过sessionId查询用户是否登录
     *
     * @param sessionId
     * @return
     */
    RpcResult<SsoUserBO> checkLoginBySessionId(String sessionId);


}
