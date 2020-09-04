package com.example.demo.example8_7;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

/**
 * ChannelOption
 */
public class Example8_7 {
    public static void main(String[] args) {
        // 创建一个AttributeKey以标识该属性
        final AttributeKey<Integer> id = AttributeKey.newInstance("ID");
        // 创建一个Bootstrap 类的实例以创建客户端Channel 并连接它们
        Bootstrap bootstrap = new Bootstrap();
        // 设置EventLoopGroup，其提供了用以处理Channel事件的EventLoop
        bootstrap.group(new NioEventLoopGroup())
                // 指定Channel的实现
                .channel(NioSocketChannel.class)
                .handler(
                        // 设置用以处理Channel 的I/O 以及数据的Channel-InboundHandler
                        new SimpleChannelInboundHandler<ByteBuf>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                                System.out.println("Received data");
                            }
                            @Override
                            public void channelRegistered(ChannelHandlerContext ctx)
                                    throws Exception {
                                // 使用AttributeKey 检索属性以及它的值
                                Integer idValue = ctx.channel().attr(id).get();
                                // do something with the idValue
                            }
                        }
                );
        // 设置ChannelOption，其将在connect()或者bind()方法被调用时被设置到已经创建的Channel 上
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        // 存储该id 属性
        bootstrap.attr(id, 123456);
        ChannelFuture future = bootstrap.connect(
                // 使用配置好的Bootstrap实例连接到远程主机
                new InetSocketAddress("www.manning.com", 80));
        future.syncUninterruptibly();
    }
}
