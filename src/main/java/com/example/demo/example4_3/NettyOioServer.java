package com.example.demo.example4_3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * 阻塞.
 *
 * @Author: zhusidao855
 * @Date: 2019/11/11 17:28
 */
public class NettyOioServer {
    public void server(int port) throws Exception {
        final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n", StandardCharsets.UTF_8));
        EventLoopGroup group = new OioEventLoopGroup();
        try {
            // 创建Server-Bootstrap
            ServerBootstrap b = new ServerBootstrap();
            // 使用OioEventLoopGroup以允许阻塞模式（旧的I/O）
            b.group(group).channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    // 指定Channel-Initializer，对于每个已接受的连接都调用它
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(
                                    // 添加一个Channel-InboundHandler-Adapter 以拦截和处理事件
                                    new ChannelHandlerAdapter() {
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) {
                                            // 将消息写到客户端，并添加ChannelFutureListener，以便消息一被写完就关闭连接

                                        }
                                    });
                        }
                    });
            // 绑定服务器以接受连接
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            // 释放所有的资源
            group.shutdownGracefully().sync();
        }
    }
}