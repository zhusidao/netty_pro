package com.example.demo.example2_2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Author: zhusidao855
 * @Date: 2019/11/11 9:22
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new EchoServer(8080).start();
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // 创建Event-LoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建Server-Bootstrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    // 指定所使用的NIO传输Channel
                    .channel(NioServerSocketChannel.class)
                    // 使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    // 添加一个EchoServer-Handler 到子Channel的ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            // EchoServerHandler 被标注为@Shareable，所以我们可以总是使用同样的实例
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            // 异步地绑定服务器；调用sync()方法阻塞等待直到绑定完成
            ChannelFuture f = b.bind().sync();
            // 获取Channel 的CloseFuture，并且阻塞当前线程直到它完成
            f.channel().closeFuture().sync();
        } finally {
            // 关闭EventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }
    }
}
