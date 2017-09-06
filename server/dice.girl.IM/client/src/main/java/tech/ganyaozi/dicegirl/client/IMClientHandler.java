package tech.ganyaozi.dicegirl.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

public class IMClientHandler extends SimpleChannelInboundHandler<BaseMessage.baseMessage> {

    private static final Logger logger = LoggerFactory.getLogger(IMClientHandler.class);

    private static final AttributeKey<String> key = AttributeKey.valueOf("uuid");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMessage.baseMessage req) throws Exception {
        // 设置本次连接的唯一ID
        if (req.getCmd() == BaseMessage.Commands.IM_SECURE_KEY) {
            ctx.channel().attr(key).set(req.getDstID());
            logger.warn("SECURE KEY SET : {}", req.getDstID());
        }
    }

}
