package com.example.netty.timer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author liangqing
 * @since 2020/12/16 16:20
 */
public class TimeSecondClientHandler extends ChannelInboundHandlerAdapter {


    //通讯读取数据时进行调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        try {
            final long l = (byteBuf.readUnsignedInt() - 2208988800L) / 1000;
            System.out.println("当前时间时:  " + new Date(l));
            ctx.close();
        } finally {
            byteBuf.release();
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}
