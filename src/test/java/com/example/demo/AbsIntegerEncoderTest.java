package com.example.demo;

import com.example.demo.example9_3.AbsIntegerEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbsIntegerEncoderTest {
    @Test
    public void testEncoded() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 1; i < 10; i++) {
            buf.writeInt(i * -1);
        }
        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
        // 写入 ByteBuf，并断言调 用 readOutbound()方法将 会产生数据
        assertTrue(channel.writeOutbound(buf));
        // 将该Channel标记为已 完成状态
        assertTrue(channel.finish());
        // read bytes
        for (int i = 1; i < 10; i++) {
            //  读取所产生的消息， 并断言它们包含了对 应的绝对值
  //          assertEquals(i, channel.readOutbound());
        }
        assertNull(channel.readOutbound());
    }
}