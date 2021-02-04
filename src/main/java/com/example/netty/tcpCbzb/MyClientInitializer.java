package com.example.netty.tcpCbzb;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * @author liangqing
 * @since 2020/12/16 11:04
 */
public class MyClientInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        //获取到pipeline 然后定义 处理器 处理器实现了ChannelInboudHandlerAdapter
        final ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyClientHandler());
    }
}
