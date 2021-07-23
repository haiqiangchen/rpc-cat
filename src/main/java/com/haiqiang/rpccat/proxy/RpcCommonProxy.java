package com.haiqiang.rpccat.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author haiqiang
 * @date 2021/7/23 18:31
 */
public class RpcCommonProxy<T> implements InvocationHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcCommonProxy.class);
    private Class<T> clazz;

    public RpcCommonProxy(Class<T> clazz) {
        this.clazz = clazz;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
