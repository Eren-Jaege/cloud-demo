package com.hello.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableAuthorizationServer
public class OAuth2Application {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2Application.class,args);
    }

    /**
     * 映射到/auth/user/，此端点由受保护服务调用，以确认OAuth2访问令牌
     * 并检索访问受保护服务的用户所分配的角色
     * @param user
     * @return
     */
    @RequestMapping(value ="/user",produces = "application/json")
    public Map<String,Object> user(OAuth2Authentication user){
        Map<String,Object> userInfo = new HashMap<>();
        userInfo.put("user",user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return userInfo;
    }
}
