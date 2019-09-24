package com.self.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author shichen
 * @create 2018/9/7
 * @desc
 */
public class NettyMessageHandler extends SimpleChannelInboundHandler<String> {


    private ThreadPoolExecutor threadPoolExecutor;

    public NettyMessageHandler(ThreadPoolExecutor threadPoolExecutor) {
        super();
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("receive client message " + s);

        //channelHandlerContext.writeAndFlush("你是谁啊");

        //所有业务交由线程池执行
        if (Objects.nonNull(threadPoolExecutor)) {
            threadPoolExecutor.execute(() -> {
                System.out.println(s);

                //处理完成，写会客户端信息
                channelHandlerContext.writeAndFlush("服务端消息");
            });
        } else {
            System.out.println(s);
            channelHandlerContext.writeAndFlush("服务端消息");
        }

    }

}
