package com.example.demo.example8_1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class Example8_1 {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        // 设置EventLoopGroup，其提供了用于处理Channel 事件的EventLoop
        bootstrap.group(group)
                // 指定要使用的Channel 实现
                .channel(NioSocketChannel.class)
                // 设 置用于处理已被接受的子Channel的I/O及数据的ChannelInbound-Handler
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("Received data");
                    }
                })
                .connect(new InetSocketAddress("www.manning.com", 80))
                .addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture)
                            throws Exception {
                        if (channelFuture.isSuccess()) {
                            System.out.println("Connection established");
                        } else {
                            System.err.println("Connection attempt failed");
                            channelFuture.cause().printStackTrace();
                        }
                    }
                });
    }
}
