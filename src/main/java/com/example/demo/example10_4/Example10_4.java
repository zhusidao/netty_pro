package com.example.demo.example10_4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * IntegerToStringDecoder
 */
public class Example10_4 {
    public static void main(String[] args) {
    }

    static class SafeByteToMessageDecoder extends ByteToMessageDecoder {
        private static final int MAX_FRAME_SIZE = 1024;

        @Override
        public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
            int readable = in.readableBytes();
            if (readable > MAX_FRAME_SIZE) {
                // 跳过所有的可读字节，抛出TooLongFrame-Exception 并通知ChannelHandler
                in.skipBytes(readable);
                throw new TooLongFrameException("Frame too big!");
            }
            // do something
        }
    }
}
