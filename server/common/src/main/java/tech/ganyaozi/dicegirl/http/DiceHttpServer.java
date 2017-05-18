package tech.ganyaozi.dicegirl.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiceHttpServer {

    private final EventLoopGroup bossGroup = new NioEventLoopGroup(5);
    private final EventLoopGroup workerGroup = new NioEventLoopGroup(20);

    private ChannelFuture future;

    @SuppressWarnings("Duplicates")
    public void startServer(boolean isClient, int port) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_BACKLOG, 100)
//                .childHandler(new HttpPipelineInitializer(isClient))
                .childHandler(new HttpAggregatorInitializer(isClient));
//                .handler(new LoggingHandler(LogLevel.INFO));
        try {
            future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void stopServer() throws InterruptedException {
            future.channel().close().sync();
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
    }


}
