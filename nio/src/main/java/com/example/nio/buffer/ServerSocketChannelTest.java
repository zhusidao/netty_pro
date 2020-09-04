package com.example.nio.buffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author ZhuSiDao
 * @date 2020/8/28
 */
public class ServerSocketChannelTest {
    // 服务端
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        // 非阻塞
        serverSocketChannel.configureBlocking(false);
        while (true) {
            // 非阻塞会立即返回
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel == null) {
                Thread.sleep(2000);
            } else {
                socketChannel.write(ByteBuffer.wrap("hello client".getBytes()));
                socketChannel.close();
            }
        }
    }
}
