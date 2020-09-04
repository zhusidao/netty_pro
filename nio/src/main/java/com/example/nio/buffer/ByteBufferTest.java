package com.example.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author ZhuSiDao
 * @date 2020/8/27
 */
public class ByteBufferTest {

    public static void main(String[] args) {

        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 看一下初始时4个核心变量的值
        System.out.println("初始时--->" + byteBuffer);

        System.out.println("--------------------------------------");

        // 添加一些数据到缓冲区中
        // put()
        String s = "Java3y";
        byteBuffer.put(s.getBytes());

        // 看一下初始时4个核心变量的值
        System.out.println("put('Java3y'.getBytes())之后--->" + byteBuffer);

        System.out.println("--------------------------------------");

        // flip()
        byteBuffer.flip();
        System.out.println("flip()完之后--->" + byteBuffer);

        System.out.println("--------------------------------------");
        System.out.println("getInt()之后" + byteBuffer.getInt());

        // get()
        System.out.println("get()调用一次返回值" + (char) byteBuffer.get());
        System.out.println("get()完之后--->" + byteBuffer);

        System.out.println("--------------------------------------");

        // compact()未读的部分提前，读取的部分置后，进入写的状态
        byteBuffer.compact();
        System.out.println("compact()完之后--->" + byteBuffer);
        System.out.println("--------------------------------------");

        // mark()
        System.out.println("mark()之前的值" + byteBuffer.mark());
        System.out.println("--------------------------------------");

        System.out.println("再次get()调用一次返回值" + (char) byteBuffer.get());
        System.out.println("再次get()之后" + byteBuffer);
        System.out.println("mark()之后" + byteBuffer.reset());

        //rewind()
        /*
         * <code>
         * position = 0;
         * mark = -1;
         * </code>
         */
        byteBuffer.rewind();
        System.out.println("rewind()完之后--->" + byteBuffer);

        System.out.println("--------------------------------------");

        // equals()类型一样，读出来的元素一样
    }
}
