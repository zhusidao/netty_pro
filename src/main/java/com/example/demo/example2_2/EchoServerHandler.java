package com.example.demo.example2_2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Author: zhusidao855
 * @Date: 2019/11/11 9:10
 */
// 标示一个Channel-Handler 可以被多个Channel 安全地共享
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    // 对于每个传入的消息都要调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println(
                "Server received: " + in.toString(CharsetUtil.UTF_8));
        // 将接收到的消息写给发送者，而不冲刷出站消息
        ctx.write(in);
    }

    /**
     * 通知ChannelInboundHandler 最后一次对channel-
     * Read()的调用是当前批量读取中的最后一条消息；
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        // 将未决消息冲刷到远程节点，并且关闭该Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    // 在读取操作期间，有异常抛出时会调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
