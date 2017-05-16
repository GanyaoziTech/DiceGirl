package tech.ganyaozi.dicegirl.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

public class IMObjectChannelHandler extends SimpleChannelInboundHandler<BaseMessage.baseMessage> {

    private static final Logger logger = LoggerFactory.getLogger(IMObjectChannelHandler.class);

    protected void messageReceived(ChannelHandlerContext ctx, BaseMessage.baseMessage msg) throws Exception {
        logger.info("message : {} ", msg);
    }
}
