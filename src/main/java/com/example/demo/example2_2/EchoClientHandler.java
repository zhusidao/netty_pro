package com.example.demo.example2_2;

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
        SimpleChannelInboundHandler {
    /**
     * 当被通知Channel是活跃的时候，发送一条消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}