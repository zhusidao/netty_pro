package com.example.demo.example8_8;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.socket.oio.OioDatagramChannel;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

/**
 * 使用Bootstrap和DatagramChannel
 */
public class Example8_8 {
    public static void main(String[] args) {
        // 创建一个Bootstrap 的实例以创建和绑定新的数据报Channel
        Bootstrap bootstrap = new Bootstrap();
        // 设置EventLoopGroup，其提供了用以处理Channel 事件的EventLoop
        bootstrap.group(new OioEventLoopGroup())
                // 指定Channel的实现
                .channel(OioDatagramChannel.class)
                .handler(
                        // 设置用以处理Channel 的I/O 以及数据的Channel-InboundHandler
                        new SimpleChannelInboundHandler<DatagramPacket>() {
                            @Override
                            protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
                                // Do something with the packet
                            }
                        }
                );
        // 调用bind()方法，因为该协议是无连接的
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(0));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture)
                    throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("Channel bound");
                } else {
                    System.err.println("Bind attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }
}
