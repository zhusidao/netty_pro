package com.example.rpc.demo2;

import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;

/**
 * @author ZhuSiDao
 * @date 2020/9/2
 */
public class ClientStarter {
    public static void main(String[] args) {
        ClientStarter clientStarter = new ClientStarter();
        TestService testService = clientStarter.getImpl(TestService.class);
        System.out.println(testService.sayHello("rpc") + "123");
    }

    @SuppressWarnings("unchecked")
    private <T> T getImpl(Class<T> targetObject) {
        //JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance 函数所需参数就可看出
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{targetObject},
                (proxy, method, args) -> {
                    if (method.getDeclaringClass() != Object.class) {
                        // 初始化入参
                        RemoteObj remoteObj = new RemoteObj(FindClassUtils.getShortName(method.getDeclaringClass().getName()),
                                method.getName(), method.getParameterTypes(), args);
                        // 初始化返回值
                        Result result = new Result();
                        // 进行系统调用
                        starter(8080, remoteObj, result);
                        // 阻塞直到获取返回值
                        // LockSupport.park(Thread.currentThread());
                        System.out.println(123);
                        return result.getObj();
                    }
                    return method.invoke(proxy, args);
                });
    }

    private static void starter(int port, RemoteObj param, Result result) throws InterruptedException {
        Thread thread = Thread.currentThread();
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        // 指定EventLoopGroup
        bootstrap.group(group)
                // 指定传输类型
                .channel(NioSocketChannel.class)
                // 指定端口连接
                .remoteAddress(new InetSocketAddress(port))
                // 指定handler
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline()
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) {
                                        // 发送一个请求className+method+param
                                        ctx.writeAndFlush(Unpooled.copiedBuffer(JSON.toJSONString(param), CharsetUtil.UTF_8));
                                    }

                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                        result.setObj(((ByteBuf) msg).toString(CharsetUtil.UTF_8));
                                        //                   LockSupport.unpark(thread);
                                    }

                                    @Override
                                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                        super.exceptionCaught(ctx, cause);
                                    }
                                });
                    }
                });
        try {
            // 连接到远程节点，阻塞等待直到连接完成
            ChannelFuture f = bootstrap.connect().sync();
            // 阻塞，直到Channel关闭
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    static class Result {
        private volatile String obj;

        public String getObj() {
            return obj;
        }

        public void setObj(String obj) {
            this.obj = obj;
        }

    }
}
