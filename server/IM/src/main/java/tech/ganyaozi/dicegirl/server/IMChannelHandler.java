package tech.ganyaozi.dicegirl.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

public class IMChannelHandler extends SimpleChannelInboundHandler<BaseMessage> {

    private static final Logger logger = LoggerFactory.getLogger(IMChannelHandler.class);
    
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMessage msg) throws Exception {

        logger.info("message received : {}." + msg);

    }
}
