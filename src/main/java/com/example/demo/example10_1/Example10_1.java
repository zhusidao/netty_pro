package com.example.demo.example10_1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * ToIntegerDecoder类拓展ByteToMessageDecoder
 */
public class Example10_1 {
    public static void main(String[] args) {

    }

    class ToIntegerDecoder extends ByteToMessageDecoder {
        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            if (in.readableBytes() >= 4) {
                out.add(in.readInt());
            }
        }
    }

}

