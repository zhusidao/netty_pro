package com.example.demo.example8_6;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

import java.net.InetSocketAddress;

/**
 * 引导和使用ChannelInitializer
 */
public class Example8_6 {
    public static void main(String[] args) throws InterruptedException {
        // 创建ServerBootstrap 以创建和绑定新的Channel
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 设置EventLoopGroup，其将提供用以处理Channel 事件的EventLoop
        bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                // 注册一个ChannelInitializerImpl的实例来设置ChannelPipeline
                .childHandler(new ChannelInitializerImpl());
        // 绑定到地址
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
        future.sync();
    }

    /**
     * 用以设置ChannelPipeline 的自定义ChannelInitializerImpl 实现
     */
    static final class ChannelInitializerImpl extends ChannelInitializer<Channel> {
        /*
         * 将所需的ChannelHandler添加到ChannelPipeline
         */
        @Override
        protected void initChannel(Channel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new HttpClientCodec());
            pipeline.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
        }
    }
}
