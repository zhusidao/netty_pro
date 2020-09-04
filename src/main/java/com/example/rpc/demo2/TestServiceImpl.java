package com.example.rpc.demo2;

/**
 * @author ZhuSiDao
 * @date 2020/9/3
 */
public class TestServiceImpl implements TestService {
    @Override
    public String sayHello(String param) {
        System.out.println("netty rpg hello");
        return "i have receive " + param;
    }
}
