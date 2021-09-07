package com.haiqiang.rpccat;


import com.haiqiang.rpccat.client.RPCFuture;
import com.haiqiang.rpccat.client.RpcClient;
import com.haiqiang.rpccat.proxy.IAsyncObjectProxy;
import com.haiqiang.rpccat.registry.ServiceDiscovery;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author haiqiang
 * @date 2018/12/4 16:36
 */
public class clientTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery("127.0.0.1:2181");
        final RpcClient rpcClient = new RpcClient(serviceDiscovery);
// Sync call
//        Exam exam = rpcClient.create(Exam.class);
//        Object result = exam.hello("haiqiang");
//        System.out.println(result);
// Async call
        IAsyncObjectProxy client = rpcClient.createAsync(Exam.class);
        RPCFuture helloFuture = client.call("hello", "World");
        String result = (String) helloFuture.get(3000, TimeUnit.MILLISECONDS);
    }
}
