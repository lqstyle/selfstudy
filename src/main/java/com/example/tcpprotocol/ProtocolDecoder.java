package com.example.tcpprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author liangqing
 * @since 2020/12/17 10:58
 * <p>
 * 实现解码器，将二进制字节流转换成业务实体
 * 实现 ReplayingDecoder ,继承在 ByteToMessageDecoder
 */
public class ProtocolDecoder extends ReplayingDecoder<Protocol> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //获取传入的字节长度
        final int i = in.readInt();

        //写入一个二进制字节数组,读取指定字节长度
        final byte[] bytes = new byte[i];
        in.readBytes(bytes);


        Protocol protocol = new Protocol();
        protocol.setLength(i);
        protocol.setBytes(bytes);

        out.add(protocol);

    }

    public static void main(String[] args) {
    }
}
