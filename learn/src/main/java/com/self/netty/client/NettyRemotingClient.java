package com.self.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shichen
 * @create 2018/9/10
 * @desc
 */
public class NettyRemotingClient {

    public static void main(String[] args) {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup(1, new ThreadFactory() {

            AtomicInteger index = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "client_io_thread_" + index.incrementAndGet());
            }
        });

        Bootstrap clientBootstrap = new Bootstrap();
        clientBootstrap.group(clientGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(
                                new StringDecoder(),
                                new StringEncoder(),
                                new NettyClientMessageHandler()
                                );
                    }
                });

        try {
            //连接server
            ChannelFuture channelFuture = clientBootstrap.connect("127.0.0.1", 8181).sync();

            for (int i = 0; i < 10; i ++) {
                channelFuture.channel().writeAndFlush("im client :" + i);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
