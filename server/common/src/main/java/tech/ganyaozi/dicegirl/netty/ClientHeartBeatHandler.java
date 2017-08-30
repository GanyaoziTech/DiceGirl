package tech.ganyaozi.dicegirl.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.proto.BaseMessage;
import tech.ganyaozi.dicegirl.utils.msg.PingPongMessage;

import java.io.IOException;

public class ClientHeartBeatHandler extends SimpleChannelInboundHandler<BaseMessage.baseMessage> {

    private static final Logger logger = LoggerFactory.getLogger(ServerHeartBeatHandler.class);

    private static final AttributeKey<String> key = AttributeKey.valueOf("uuid");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMessage.baseMessage msg) throws Exception {
        if (msg.getCmd() == BaseMessage.Commands.IM_PING_PONG) {
            logger.debug("Pong");
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Connection to server ACTIVE !! ");
        super.channelActive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            switch (((IdleStateEvent) evt).state()) {
                case ALL_IDLE:
                case WRITER_IDLE:
                    logger.debug("Ping");
                    ctx.writeAndFlush(PingPongMessage.getPingInstance(ctx.channel().attr(key).get()));
                    break;
                case READER_IDLE:
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
}
