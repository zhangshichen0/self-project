package com.self.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author shichen
 * @create 2018/9/7
 * @desc
 */
public class NettyMessageHandler extends SimpleChannelInboundHandler<String> {

    public NettyMessageHandler() {
        super();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("receive client message " + s);

        channelHandlerContext.writeAndFlush("你是谁啊");
    }

}
