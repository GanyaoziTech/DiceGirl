package tech.ganyaozi.dicegirl.server.netty;

import akka.actor.ActorRef;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class DiceIMServer {

    private static final int BOSS_GROUP_SIZE = 1;

    private static final int WORKER_GROUP_SIZE = 10;

    private static EventLoopGroup bossGroup = new NioEventLoopGroup(BOSS_GROUP_SIZE);
    private static EventLoopGroup workerGroup = new NioEventLoopGroup(WORKER_GROUP_SIZE);

    public static void init(int port, ActorRef bussinessCenter) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        if (isLinux()) {
            bossGroup = new NioEventLoopGroup(BOSS_GROUP_SIZE);
            workerGroup = new NioEventLoopGroup(WORKER_GROUP_SIZE);
        } else {
            bossGroup = new EpollEventLoopGroup(BOSS_GROUP_SIZE);
            workerGroup = new EpollEventLoopGroup(WORKER_GROUP_SIZE);
        }
        bootstrap.group(bossGroup, workerGroup)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_BACKLOG, 100)
                .childHandler(new IMChannelInitializer(bussinessCenter));
        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    private static boolean isLinux(){
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }
}
