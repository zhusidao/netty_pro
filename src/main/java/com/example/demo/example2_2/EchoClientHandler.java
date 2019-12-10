package com.example.demo.example2_2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @Author: zhusidao855
 * @Date: 2019/11/11 9:51
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends
        SimpleChannelInboundHandler<ByteBuf> {
    /**
     * 当被通知Channel是活跃的时候，发送一条消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    /**
     * 在发生异常时，记录错误并关闭Channel
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 记录已接收消息的转储
     */
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        System.out.println("Client received: " + byteBuf.toString(CharsetUtil.UTF_8));
    }
}