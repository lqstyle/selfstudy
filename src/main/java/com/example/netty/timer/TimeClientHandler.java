package com.example.netty.timer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 *   解决 netty 拆包，粘包
 *  1. 换行符  （LineBasedFrameDecoder）
 *  2. 分隔符    (DelimiterBasedFrameDecoder)
 *  3. 指定字长，不足补齐 (FixedLengthFrameDecoder)
 */

/**
 * @author liangqing
 * @since 2020/12/16 14:24
 *
 * 接收过来的数据流可能会被拆分到不同的数据段内，
 * 出现下标数组越界异常
 *
 *可以借助缓存，当大小大于等于传入的字节数组，再读取
 * 此种场景比较适合定长的场景，如果不定长，还是会抛出异常
 *
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    ByteBuf bytes ;

    //通讯读取数据时进行调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        bytes.writeBytes(byteBuf);

        if(bytes.readableBytes()>=4){
            try {
                final long l = (bytes.readUnsignedInt() - 2208988800L) / 1000;
                System.out.println("当前时间时:  " + new Date(l));
                ctx.close();
            } finally {
                byteBuf.release();
            }
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        bytes = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        bytes.release();
        bytes = null;
    }
}
