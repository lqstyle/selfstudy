package com.example.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @author liangqing
 * @since 2020/12/16 10:18
 * 定义处理器
 * <p>
 * pipeline中的处理器
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 通道读取
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        System.out.println("客户端输入的数据为:  " + new String(bytes, Charset.forName("utf-8"))
        + ctx.channel().remoteAddress());

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
