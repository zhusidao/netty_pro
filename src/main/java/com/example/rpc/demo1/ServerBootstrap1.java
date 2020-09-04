package com.example.rpc.demo1;

public class ServerBootstrap1 {

    public static void main(String[] args) {
        UserServiceImpl.startServer("localhost", 8990);
    }
    
}