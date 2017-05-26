package tech.ganyaozi.dicegirl.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.msg.ClientInitMessage;
import tech.ganyaozi.dicegirl.msg.PingPongMessage;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

import java.io.IOException;
import java.util.UUID;

public class ServerHeartBeatHandler extends SimpleChannelInboundHandler<BaseMessage.baseMessage> {

    private static final Logger logger = LoggerFactory.getLogger(ServerHeartBeatHandler.class);

    private final AttributeKey<String> key = AttributeKey.valueOf("uuid");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMessage.baseMessage msg) throws Exception {
        if (msg.getCmd() != BaseMessage.Commands.IM_PING_PONG) {
            ctx.fireChannelRead(msg);
        } else {
            logger.debug("receive ping from client : {} ", msg.getSrcID());
            ctx.writeAndFlush(PingPongMessage.getPongInstance(ctx.channel().attr(key).get()));
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UUID uuid = UUID.randomUUID();
        ctx.channel().attr(key).set(uuid.toString());
        ChannelFuture future = ctx.channel().writeAndFlush(ClientInitMessage.getInstance(uuid));
        logger.info("New Channel Active : define UUID as {} ", uuid);
        future.addListener(future1 -> {
            if (future.isCancelled() || !future.isSuccess()) {
                ctx.channel().close();
            }
        });
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            switch (((IdleStateEvent) evt).state()) {
                case READER_IDLE:
                    // 过久没有收到数据读取时，自动关闭连接
                    logger.error("READ_IDLE ! close this channel!! {}", ctx.channel().attr(key).get());
                    ctx.close();
                    break;
                default:
                    break;
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof IOException) {
            logger.error("Exception : {}", cause.getMessage());
            return;
        }
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("Channel unregistered ! {} ", ctx.channel().attr(key).get());
        super.channelUnregistered(ctx);
    }
}
