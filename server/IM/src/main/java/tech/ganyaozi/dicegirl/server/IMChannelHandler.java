package tech.ganyaozi.dicegirl.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proto.BaseMessage;
import tech.ganyaozi.dicegirl.msg.ClientInitMessage;

import java.util.UUID;

public class IMChannelHandler extends SimpleChannelInboundHandler<BaseMessage.baseMessage> {

    private static final Logger logger = LoggerFactory.getLogger(IMChannelHandler.class);
    private final AttributeKey<String> key = AttributeKey.valueOf("uuid");

    protected void messageReceived(ChannelHandlerContext ctx, BaseMessage.baseMessage msg) throws Exception {
        logger.info("message : {} ", msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UUID uuid = UUID.randomUUID();
        ctx.attr(key).set(uuid.toString());
        ChannelFuture future = ctx.channel().writeAndFlush(ClientInitMessage.getInstance(uuid));
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
                    logger.error("READ_IDLE ! close this channel!! {}",ctx.attr(key).get());
                    ctx.close();
                    break;
                default:
                    break;
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
