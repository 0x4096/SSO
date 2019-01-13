## 说明
此目录包括两个模块,sso-core与sso-core-starter,后者是在前者的基础上进行了自动配置功能。  

关于spring-boot自动配置化,需要引用一下依赖  
```$xslt
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-autoconfigure</artifactId>
    <version>2.1.1.RELEASE</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <version>2.1.1.RELEASE</version>
    <optional>true</optional>
</dependency>
```

关于自动配置这块代码我有引用dubbo-spring-boot-starter的源码,主要是util包下的两个工具类。


## 使用方式

### sso-core

引用依赖

```
<dependency>
    <groupId>com.wy.sso.core</groupId>
    <artifactId>sso-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

使用此依赖,spring-boot将不会自动读取客户端的配置,例如:

```$xslt
# SSO服务器地址
wy.sso.server.serverUrl=http://127.0.0.1
# SSO服务登录URI
wy.sso.server.loginUri=/login
# SSO服务登出URI
wy.sso.server.logoutUri=/logout
# 是否启用SSO拦截器,boolean类型
wy.sso.client.enable=true
# 接入SSO的当前应用系统的应用ID
wy.sso.client.appId=client-one
# SSO登录拦截器拦截当前系统URI,默认为/**
wy.sso.client.includedPaths=
# SSO登录拦截器放行当前系统URI
wy.sso.client.excludedPaths=
```
需要在客户端写代码触发SSO拦截器,即sso-client模块的 com.wy.sso.client.configuration.SsoClientInitConfig

### sso-core-starter

引用依赖

```
<dependency>
    <groupId>com.wy.sso.core</groupId>
    <artifactId>sso-core-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

使用此依赖spring-boot将自动读取配置文件,如果不启用SSO拦截器可以将 wy.sso.client.enable 设置为false


