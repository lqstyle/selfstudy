package com.example.netty.timer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * @author liangqing
 * @since 2020/12/16 14:25
 */
public class TimeHandlerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        final ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new TimeDecoder(),new TimeSecondClientHandler());
    }
}
