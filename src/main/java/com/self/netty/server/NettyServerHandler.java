package com.self.netty.server;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author shichen
 * @create 2018/9/7
 * @desc
 */
public class NettyServerHandler extends ChannelDuplexHandler {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("netty server register");

        super.channelRegistered(ctx);


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("netty server active");

        super.channelActive(ctx);
    }
}
