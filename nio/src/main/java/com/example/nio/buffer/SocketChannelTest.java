package com.example.nio.buffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author ZhuSiDao
 * @date 2020/8/28
 */
public class SocketChannelTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(1234));
        while (!socketChannel.finishConnect()) {
            Thread.sleep(2000);
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        socketChannel.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }
        socketChannel.write(ByteBuffer.wrap("hello server".getBytes()));
        Thread.sleep(10000);
        socketChannel.close();
    }
}
