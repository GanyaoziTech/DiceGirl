package tech.ganyaozi.dicegirl.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

import java.net.SocketAddress;
import java.util.Date;

public class IMClient {

    private static final int BOSS_GROUP_SIZE = 1;

    private static EventLoopGroup bossGroup = new NioEventLoopGroup(BOSS_GROUP_SIZE);

    public Channel channel;

    public void connect(SocketAddress address) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(bossGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .handler(new IMChannelInitializer());
        try {
            ChannelFuture future = bootstrap.connect(address).sync();
            this.channel = future.channel();
            hello(future);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
        }
    }

    private void hello(ChannelFuture future) throws InterruptedException {
        if (future.isSuccess()) {
            byte[] msg = BaseMessage.baseMessage.newBuilder()
                    .setId(1)
                    .setTimeStamp(new Date().toString())
                    .setContent("Hello world")
                    .build().toByteArray();
            future.addListener(future1 -> {
                future.channel().writeAndFlush(msg);
            });
        }
    }


}
