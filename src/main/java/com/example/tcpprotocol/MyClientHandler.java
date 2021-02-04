package com.example.tcpprotocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;


/**
 * @author liangqing
 * @since 2020/12/16 10:56
 * 粘包拆包问题
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Protocol> {

    private int count =0 ;

    private int sum;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Protocol protocol) throws Exception {
        byte[] bytes = new byte[protocol.getLength()];

        System.out.println();

        System.out.println(new String(bytes, Charset.forName("utf-8"))
                + ctx.channel().remoteAddress());

        System.out.println("服务端返回 ： "+(++this.sum));
        System.out.println();
        System.out.println();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发送十次数据，验证粘包拆包问题 ,定义协议对象
        for (int i = 0; i < 10000; i++) {
            Protocol protocol = new Protocol();
            String s = "hello,server,我是发送方 ";
            protocol.setLength(s.getBytes().length);
            protocol.setBytes(s.getBytes());
            ctx.writeAndFlush(protocol);
            ++count;
        }
        System.out.println("客户端发送的数量:  "+this.count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
