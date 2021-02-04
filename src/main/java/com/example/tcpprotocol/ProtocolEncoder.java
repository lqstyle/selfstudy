package com.example.tcpprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * @author liangqing
 * @since 2020/12/17 10:53
 *
 * 此处定义编码器，把业务数据 转换成二进制字节流传输 需要继承MessageToByteEncoder
 * 此处不需要设置编码，在clientHander中已经设置编码为utf-8
 */
public class ProtocolEncoder extends MessageToByteEncoder<Protocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Protocol msg, ByteBuf out) throws Exception {
        System.out.println("编码器被调用");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getBytes());
    }
}
