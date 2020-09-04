package com.example.rpc.demo2;

import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ZhuSiDao
 * @date 2020/9/2
 */
public class ServerStarter {

    public static void main(String[] args) throws InterruptedException {
        new ServerStarter().starter(8080);
    }

    private void starter(int port) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(port)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline()
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                        try {
                                            ctx.writeAndFlush(Unpooled.copiedBuffer(
                                                    JSON.toJSONString(invoke(JSON.parseObject(((ByteBuf) msg).toString(CharsetUtil.UTF_8),
                                                            RemoteObj.class))), CharsetUtil.UTF_8));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    }
                });
        try {
            // 异步地绑定服务器；调用sync()方法阻塞等待直到绑定完成
            ChannelFuture f = bootstrap.bind().sync();
            // 获取Channel的CloseFuture，并且阻塞当前线程直到它完成
            f.channel().closeFuture().sync();
        } finally {
            // 关闭EventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }
    }

    private static Object invoke(RemoteObj remoteObj) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        Class<?> clazz = FindClassUtils.getFirstImpl(remoteObj.getServerName());
        Method method = clazz.getMethod(remoteObj.getMethodName(), remoteObj.getArgumentsTypes());
        return method.invoke(clazz.newInstance(), remoteObj.getArgumentsValues());
    }

}