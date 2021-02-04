package com.example.netty.tcpCbzb;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;


/**
 * @author liangqing
 * @since 2020/12/16 10:56
 * 粘包拆包问题
 */
public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count =0 ;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        byte[] bytes = new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(bytes);
        System.out.println();

        System.out.println(new String(bytes, Charset.forName("utf-8"))
                + ctx.channel().remoteAddress());
        System.out.println();
        System.out.println();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发送十次数据，验证粘包拆包问题
        for (int i = 0; i < 10000; i++) {
            ByteBuf buffer = Unpooled.copiedBuffer("hello,server,我是发送方 ", Charset.forName("utf-8"));
            ctx.writeAndFlush(buffer);
            ++count;
        }
        System.out.println("客户端发送的数量:  "+this.count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
