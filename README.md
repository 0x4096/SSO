## 说明

此单点登录项目参考了 https://github.com/xuxueli/xxl-sso  

xxl-sso设计上个人觉得欠妥,以下是我的想法:  
1. 客户端即接入单点登录的应用还需要初始化Redis配置信息,这一做法欠妥。
2. 存储用户信息的过程不应该在sso-core模块中,这样做将导致接入单点登录的应用可以直接查看用户信息存储过程和直接获取用户信息。
3. 请求链接携带参数会丢失,即访问类似 `/test?key=value ` 携带参数的链接,如果此时跳转至登录页面,`?key=value` 数据会被丢失。
4. 生成的SESSIONID,直接暴露在地址栏,不安全的一种体现。


第一次接触单点登录是在clone了xxl-sso项目后进行了整体的debug,弄明白了整个流程后才有这一项目。  
以下是关于以上几点的问题进行改造:  
1. 去除初始化Redis连接工具,并将filter改造为intercept。
2. 服务端提供RPC服务,接入单点登录的应用系统通过RPC服务获取用户数据。
3. 参数丢失问题,服务端需要进行处理。前端提供两种思路: 

    a. 直接截取,可以通过js的`window.location.href` 获取整个URL,然后截取 ?redirect_url=http://0x4096.com/?test=666&666=test
    
    b. 服务端使用URLEncode进行处理,前端直接获取参数key=redirect_url,value=URL编码后的内容,包括参数
4. 多做一次重定向,避免不必要的参数直接暴露在浏览器地址栏。
5. 将过滤器改造为拦截器实现


## 运行环境
1. jdk1.8
2. maven
3. redis
4. zookeeper



