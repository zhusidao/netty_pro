package com.example.nio.buffer;

import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ZhuSiDao
 * @date 2020/8/27
 */
public class FileInputStreamTest {

    public static void main(String[] args) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("application.properties");
        FileInputStream input = new FileInputStream(url.getPath());
        FileChannel channel = input.getChannel();
        channel.write(ByteBuffer.wrap("hello".getBytes()));
        input.close();
        channel.close();
    }
}
