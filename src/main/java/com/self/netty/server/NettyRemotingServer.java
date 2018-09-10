package com.self.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * netty server端
 *
 * @author shichen
 * @create 2018/9/7
 * @desc
 */
public class NettyRemotingServer {

    public static void main(String[] args) {

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //创建boss事件处理线程组
        NioEventLoopGroup bossEventLoopGroup = new NioEventLoopGroup(1, new ThreadFactory() {

            AtomicInteger index = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"BossEventLoopGroup_" + index.incrementAndGet());
            }
        });

        NioEventLoopGroup workerEventLoopGroup = new NioEventLoopGroup(8, new ThreadFactory() {
            AtomicInteger index = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "WorkerEventLoopGroup_" + index.incrementAndGet());
            }
        });

        serverBootstrap.group(bossEventLoopGroup, workerEventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_KEEPALIVE, false)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_SNDBUF, 65535)
                .childOption(ChannelOption.SO_RCVBUF, 65535)
                .localAddress(new InetSocketAddress(8181))
                .handler(new NettyServerHandler())
                //处理客户端连接传递过来的数据的handler初始化
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new NettyDecoder(),
                                new NettyConnectHandler(),
                                new NettyMessageHandler());
                    }
                });

        try {
            ChannelFuture channelFuture = serverBootstrap.bind().sync().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    System.out.println("bind port listen complete");
                }
            });

            InetSocketAddress addr = (InetSocketAddress) channelFuture.channel().localAddress();
            System.out.println("监听端口:" + addr.getPort());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
