package com.example.demo.example9_3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 扩展 MessageToMessageEncoder 以 将一个消息编码为另外一种格式
 */
public class AbsIntegerEncoder extends MessageToMessageEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) {
        while (in.readableBytes() >= 4) {
            // 从输入的 ByteBuf 中读取下一个整数， 并且计算其绝对值
            int value = Math.abs(in.readInt());
            // 将该整数写入到编码 消息的 List 中
            out.add(value);
        }
    }
}