package com.self.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.string.StringDecoder;

import java.util.List;

/**
 * @author shichen
 * @create 2018/9/7
 * @desc
 */
public class NettyDecoder extends StringDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        super.decode(ctx, msg, out);
        //System.out.println(out.toString());
    }
}
