package com.example.rpc.demo1;

public class ClientBootstrap {

    public static final String providerName = "UserService#sayHello#";

    public static void main(String[] args) throws InterruptedException {
        RpcConsumer consumer = new RpcConsumer();
        // 创建一个代理对象
        UserService service = (UserService) consumer.createProxy(UserService.class, providerName);
        for (;;) {
            Thread.sleep(1000);
            System.out.println(service.sayHello("are you ok ?"));
        }
    }
    
}