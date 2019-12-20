package com.example.demo.example10_2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * ToIntegerDecoder类拓展ReplayingDecoder
 */
public class Example10_2 {
    public static void main(String[] args) {
    }

    class ToIntegerDecoder extends ReplayingDecoder<Void> {
        @Override
        public void decode(ChannelHandlerContext ctx, ByteBuf in,
                           List<Object> out) throws Exception {
            out.add(in.readInt());
        }
    }
}
