package tech.ganyaozi.dicegirl.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;

public class DiceHttpsServer {
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(5);
    private final EventLoopGroup workerGroup = new NioEventLoopGroup(20);

    private SslContext sslContext;

    public DiceHttpsServer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @SuppressWarnings("Duplicates")
    public void startServer(boolean isClient, int port) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new SSLChannelInitializer(sslContext,isClient))
                .handler(new HttpAggregatorInitializer(isClient));
        try {
            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
