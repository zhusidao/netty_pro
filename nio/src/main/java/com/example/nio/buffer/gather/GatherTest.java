package com.example.nio.buffer.gather;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ZhuSiDao
 * @date 2020/8/27
 */
public class GatherTest {
    public static void main(String[] args) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("application.properties");
        RandomAccessFile randomAccessFile = new RandomAccessFile(
                url.getPath(), "rw");
        randomAccessFile.seek(0);

        FileChannel fileChannel = randomAccessFile.getChannel();
        System.out.println("FileChannel position " + fileChannel.position());

        ByteBuffer buffer = ByteBuffer.allocate(100);
        String str = "hello";
        buffer.put(str.getBytes());
        buffer.flip();

        ByteBuffer buffer2 = ByteBuffer.allocate(100);
        String str2 = " world";
        buffer2.put(str2.getBytes());
        buffer2.flip();

        ByteBuffer[] bufferArray = { buffer, buffer2 };
        fileChannel.write(bufferArray);

        randomAccessFile.close();
        fileChannel.close();

    }
}
