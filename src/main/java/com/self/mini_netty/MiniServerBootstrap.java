package com.self.mini_netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * 用于创建服务端io
 *
 * @author shichen
 * @create 2018-12-25
 * @desc
 */
public class MiniServerBootstrap {

    private MiniNioEventLoopGroup miniNioEventLoopGroup;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public MiniServerBootstrap(MiniNioEventLoopGroup miniNioEventLoopGroup) throws IOException {
        this.miniNioEventLoopGroup = miniNioEventLoopGroup;
        this.selector = SelectorProvider.provider().openSelector();
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.configureBlocking(false);
        this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
    }

    public void bind(String host, int port) throws IOException {
        this.serverSocketChannel.bind(new InetSocketAddress(host, port));
        while (true) {
            int select = this.selector.select(1000);
            if (select > 0) {
                Set<SelectionKey> selectionKeys = this.selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    this.serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    this.register(this.serverSocketChannel.accept());
                    iterator.remove();
                }
            }
        }
    }

    /**
     * 分发注册
     *
     * @param channel
     */
    private void register(SocketChannel channel) {
        this.miniNioEventLoopGroup.next().registerChannel(channel, SelectionKey.OP_READ);
    }
}
