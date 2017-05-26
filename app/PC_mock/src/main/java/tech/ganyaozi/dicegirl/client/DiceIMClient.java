package tech.ganyaozi.dicegirl.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DiceIMClient {

    private static final Logger logger = LoggerFactory.getLogger(DiceIMClient.class);

    private static final int BOSS_GROUP_SIZE = 1;
    public static Channel channel;
    private static EventLoopGroup eventLoopGroup = new NioEventLoopGroup(BOSS_GROUP_SIZE);
    private static ExecutorService service = Executors.newSingleThreadExecutor();

    private DiceIMClient() {
    }

    public static Channel startServer(SocketAddress address) {
        if (eventLoopGroup.isTerminated() || eventLoopGroup.isShutdown()) {
            eventLoopGroup = new NioEventLoopGroup(BOSS_GROUP_SIZE);
        }
        if (service.isShutdown()) {
            service = Executors.newSingleThreadExecutor();
        }
        service.submit(() -> connect(address));
        return DiceIMClient.channel;
    }

    public static void shutdownGracefully() {
        DiceIMClient.channel.close();
        service.shutdown();
        try {
            service.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void connect(SocketAddress address) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .handler(new IMChannelInitializer());
        try {
            ChannelFuture future = bootstrap.connect(address).sync();
            DiceIMClient.channel = future.channel();
            future.channel().closeFuture().sync();
            logger.error("Channel Closed !!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
