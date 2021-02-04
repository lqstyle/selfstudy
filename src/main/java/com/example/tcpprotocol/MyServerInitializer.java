package com.example.tcpprotocol;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * @author liangqing
 * @since 2020/12/16 11:02
 */
public class MyServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        final ChannelPipeline pipeline = ch.pipeline();

        //在双向队列中添加解码器
        pipeline.addLast(new ProtocolDecoder());
        //在双向队列中添加编码器
        pipeline.addLast(new ProtocolEncoder());

        pipeline.addLast(new MyServerHandler());
    }
}
