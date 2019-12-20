package com.example.demo.example10_3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * IntegerToStringDecoder
 */
public class Example10_3 {
    public static void main(String[] args) {
    }

    class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {
        @Override
        protected void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) {
            out.add(String.valueOf(msg));
        }
    }
}
