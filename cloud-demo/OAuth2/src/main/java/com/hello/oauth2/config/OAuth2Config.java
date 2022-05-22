package com.hello.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory()//支持内存存储和JDBC存储
                .withClient("orderservice")//注册的应用程序名称
                .secret("thisissercret")//密钥
                .authorizedGrantTypes("password","refresh_token","client_credentials")//授权类型
                .scopes("webclient","mobileclient");//请求OAuth2服务器访问令牌时可以操作的范围，可以限制客户端应用程序可以执行的操作
    }


    /**
     * 该方法定义AuthorizationServerEndpointsConfigurer中使用的不同组件
     * 这段代码告诉Spring使用Spring提供的默认验证管理器和用户详细信息服务
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }


}
