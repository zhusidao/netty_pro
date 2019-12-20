/*
package com.example.demo.example4_3;

public class Test {
    Channel channel = ...
    ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
    ChannelFuture cf = channel.writeAndFlush(buf);
    cf.addListener(new ChannelFutureListener() {
        @Override
        public void operationComplete (ChannelFuture future){
            if (future.isSuccess()) {
                System.out.println("Write successful");
            } else {
                System.err.println("Write error");
                future.cause().printStackTrace();
            }
        }
    });

    final Channel channel = ...
    final ByteBuf buf = Unpooled.copiedBuffer("your data",
            CharsetUtil.UTF_8).retain();
    Runnable writer = new Runnable() {
        @Override
        public void run() {
            channel.writeAndFlush(buf.duplicate());
        }
    };
    Executor executor = Executors.newCachedThreadPool();
    // write in one thread
    executor.execute(writer);
    // write in another thread
    executor.execute(writer);


    final Channel channel = ...
    final ByteBuf buf = Unpooled.copiedBuffer("your data",
            CharsetUtil.UTF_8).retain();
    Runnable writer = new Runnable() {
        @Override
        public void run() {
            channel.writeAndFlush(buf.duplicate());
        }
    };
    Executor executor = Executors.newCachedThreadPool();
    // write in one thread
    executor.execute(writer);
    // write in another thread
    executor.execute(writer);
}
*/
