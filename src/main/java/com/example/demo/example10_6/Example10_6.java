package com.example.demo.example10_6;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * ShortToByteEncoder编码器
 */
public class Example10_6 {
    public static void main(String[] args) {
    }

    /**
     * 扩展了MessageToMessageEncoder
     */
    static class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {
        @Override
        public void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) {
            // 将Integer 转换为String，并将其添加到List中
            out.add(String.valueOf(msg));
        }
    }
}
