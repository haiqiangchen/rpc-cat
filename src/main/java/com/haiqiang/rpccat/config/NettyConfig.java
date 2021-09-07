package com.haiqiang.rpccat.config;

import com.haiqiang.rpccat.registry.ServiceRegistry;
import com.haiqiang.rpccat.server.BootNetty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author haiqiang
 * @date 2021/9/6 17:14
 */
@Configuration
public class NettyConfig {

    @Value("${zookeeper.url}")
    private String zookeeper;

    @Value("${netty.server.url}")
    private String serverUrl;

    @Bean
    public BootNetty getBootNetty(){
        //提前注入netty服务器
        ServiceRegistry serviceRegistry=new ServiceRegistry(zookeeper);
        BootNetty bootNetty=new BootNetty(serverUrl,serviceRegistry);
        return bootNetty;
    }
}
