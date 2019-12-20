package com.example.demo.example10_5;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * ShortToByteEncoder编码器
 */
public class Example10_5 {
    public static void main(String[] args) {
    }

    /**
     * 扩展了MessageToByteEncoder
     */
    static class ShortToByteEncoder extends MessageToByteEncoder<Short> {
        @Override
        public void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out) {
            // 将Short 写入ByteBuf 中
            out.writeShort(msg);
        }
    }
}
