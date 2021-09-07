package com.haiqiang.rpccat;

import com.haiqiang.rpccat.annotation.Service;
import com.haiqiang.rpccat.registry.ServiceRegistry;
import com.haiqiang.rpccat.server.BootNetty;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Map;

@SpringBootApplication
public class RpcCatApplication {

    private static final Logger logger= LoggerFactory.getLogger(RpcCatApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RpcCatApplication.class, args);
    }
}
