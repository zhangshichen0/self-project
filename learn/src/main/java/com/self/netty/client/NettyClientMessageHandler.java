package com.self.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shichen
 * @create 2018/9/10
 * @desc
 */
public class NettyClientMessageHandler extends SimpleChannelInboundHandler<String> {

    private AtomicInteger count = new AtomicInteger();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        if (count.getAndIncrement() < 10) {
            ctx.writeAndFlush("hello server, i am client " + count.getAndIncrement());
        }
        System.out.println("receive server msg :" + msg);
    }
}
