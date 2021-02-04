package com.example.tcpprotocol;

import io.netty.channel.ChannelHandlerContext;
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
public class MyServerHandler extends SimpleChannelInboundHandler<Protocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Protocol protocol) throws Exception {
        System.out.println("客户端输入的数据为:  " + new String(protocol.getBytes(), Charset.forName("utf-8"))
                + ctx.channel().remoteAddress());
        System.out.println();
        System.out.println();
        System.out.println("服务端接收到的数量:" + (++this.count));

        //服务端返回数据
        final String s = UUID.randomUUID().toString();
        Protocol protocol1 = new Protocol();
        protocol1.setLength(s.getBytes("utf-8").length);
        protocol1.setBytes(s.getBytes("utf-8"));
        ctx.writeAndFlush(protocol1).sync();
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
