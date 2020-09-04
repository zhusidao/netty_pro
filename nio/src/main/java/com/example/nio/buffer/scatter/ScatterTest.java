package com.example.nio.buffer.scatter;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ZhuSiDao
 * @date 2020/8/27
 */
public class ScatterTest {
    public static void main(String[] args) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("application.properties");
        RandomAccessFile randomAccessFile = new RandomAccessFile(
                url.getPath(), "rw");
        randomAccessFile.seek(0);
        // FileChannel只能由RandomAccessFile、FileInputStream或 FileOutputStream创建
        // 文件通道总是阻塞的，因为不能被置于非阻塞模式。
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(5);
        ByteBuffer buffer2 = ByteBuffer.allocate(100);
        ByteBuffer[] bufferArray = {buffer, buffer2};
        fileChannel.read(bufferArray);
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
        System.out.println("");
        buffer2.flip();
        while (buffer2.hasRemaining()) {
            System.out.print((char) buffer2.get());
        }
        randomAccessFile.close();
        fileChannel.close();
    }
}
