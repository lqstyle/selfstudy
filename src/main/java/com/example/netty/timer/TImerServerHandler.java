package com.example.netty.timer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

/**
 * @author liangqing
 * @since 2020/12/16 14:10
 */
public class TImerServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 该方法在连接被建立并且准备通信时进行调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //创建一个分配四字节的 ByteBuf
        final ByteBuf byteBuf = ctx.alloc().buffer(4);

        //获取当前时间的整形
        final long currentLong = System.currentTimeMillis() / 1000 + 2208988800L;
        byteBuf.writeInt((int) currentLong);

        System.out.println("当前时间是 :" +new Date(currentLong));
        //将buffer写入到ChannelHandlerContext
        ctx.writeAndFlush(byteBuf);


    }

/*    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }*/

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
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
