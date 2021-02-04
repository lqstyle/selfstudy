package com.example.netty.tcpCbzb;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @author liangqing
 * @since 2020/12/16 10:18
 * 定义处理器
 * <p>
 * pipeline中的处理器
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        System.out.println();


        System.out.println("客户端输入的数据为:  " + new String(bytes, Charset.forName("utf-8"))
                + ctx.channel().remoteAddress());
        System.out.println();
        System.out.println();
        System.out.println("服务端接收到的数量:"+(++this.count));

        //服务端返回数据
        final String s = UUID.randomUUID().toString();
        final ByteBuf byteBuf1 = Unpooled.copiedBuffer(s, Charset.forName("utf-8"));
        ctx.writeAndFlush(byteBuf1).sync();
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
