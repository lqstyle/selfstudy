package com.example.tcpprotocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author liangqing
 * @since 2020/12/16 10:11
 * <p>
 * ByteBuffer和ByteBuf区别
 * ByteBuf 采用读写分离的策略  readindex writeindex
 * 初始都为0 ，随着数据写入 ，大小为 N  数据读取，也会增加读指针
 * 初始默认大小256 ，最大值 Integer.maxValue，操作不需要额外的方法
 * 可以动态扩容
 * <p>
 * ByteBuffer  mark<=position<=limit<=capacity
 * 如果需要切换模式，flip和 rewind  clear 复位，不清除元素
 * 必须指定分配的大小，不允许动态扩容
 * allocate（分配在堆上）  allocateDirect（分配在直接内存）
 * Netty 使用的是ByteBuf
 * jdk  nio使用的是ByteBUffer
 * 所以netty 的ByteBuf提供了转换
 * <p>
 * ByteBuffer nioBuffer()  将ByteBuf转换成ByteBuffer
 *
 * 通过自定义协议 + 编码 解码解决
 *
 *
 * ·总结：
 * 对于tcp 拆包粘包 主要的原因还是因为  对于二进制流的消息传输是无消息边界的
 * 主要是tcp缓冲区和应用缓冲区的 字节数据传输
 * 在tcp端做了nagle 算法优化，对于数据包会做粘包处理
 * 所以需要做 自定义协议（字节度和字节数组）和编码解码器进行处理
 * 需要在服务端和客户端的pipeline双向队列中添加编码器和解码器
 * 客户端，需要对传送的字节做字符集处理   封装到自定义协议中
 * 服务端，需要对响应的数据做字符集处理   封装到自定义协议中
 * 常用的编码器  MessageToByteEncoder 将业务数据转换成字节流
 * 常用的解码器  ByteToMessageDecoder 将字节流转换成业务数据
 * ReplayingDecoder
 *
 * 对于 netty提供的粘包处理方式有四种 ByteToMessageDecoder
 *  1. 每次读取指定长度的字节数组  FixedLengthFrameDecoder
 *  2. 按照换行符读取            LineBasedFrameDecoder
 *  3. 自定义分隔符              DelimiterBasedFrameDecoder
 *  4. 基于数据包长度的解析       LengthFieldBasedFrameDecoder  （消息头（定义数据包长度）和消息体（数据包内容））
 */
public class MyServer {

    public static void main(String[] args) throws InterruptedException {
        //netty模型 bossGroup workGroup
        final EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        final NioEventLoopGroup workLoopGroup = new NioEventLoopGroup();

        try {
            //定义配置参数  辅助启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossLoopGroup, workLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);;

            //绑定端口
            final ChannelFuture channelFuture = serverBootstrap.bind(6668).sync();

            //异步关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            //关闭两组线程池
            bossLoopGroup.shutdownGracefully();
            workLoopGroup.shutdownGracefully();
        }

    }
}
