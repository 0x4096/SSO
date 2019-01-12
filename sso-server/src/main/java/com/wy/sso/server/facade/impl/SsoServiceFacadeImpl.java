package com.wy.sso.server.facade.impl;


import com.wy.sso.core.facade.RpcResult;
import com.wy.sso.core.facade.SsoServiceFacade;
import com.wy.sso.core.model.SsoUserBO;
import com.wy.sso.core.util.CookieUtils;
import com.wy.sso.server.constant.SsoServerConstants;
import com.wy.sso.server.util.JedisUtils;
import com.wy.sso.server.util.SsoUserLoginUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2019/1/5
 * @instructions: 实现RPC服务接口
 */
public class SsoServiceFacadeImpl implements SsoServiceFacade {

    /**
     * 通过sessionId查询Redis中是否有该数据并返回结果
     *
     * @param sessionId
     * @return
     */
    @Override
    public RpcResult<SsoUserBO> checkLoginBySessionId(String sessionId) {
        RpcResult<SsoUserBO> rpcResult = new RpcResult<>();
        if(StringUtils.isBlank(sessionId)){
            rpcResult.setSuccess(false);
            return rpcResult;
        }

        SsoUserBO ssoUserBO = SsoUserLoginUtils.loginCheck(sessionId);
        if(ssoUserBO != null){
            /* 延迟登录用户数据过期时间 */
            JedisUtils.expire(sessionId, SsoServerConstants.EXPIRE_TIME);
            rpcResult.setData(ssoUserBO);
            return rpcResult;
        }
        rpcResult.setSuccess(false);
        return rpcResult;
    }
}
