package tech.ganyaozi.dicegirl.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

public class IMProtoBufHandler extends SimpleChannelInboundHandler<BaseMessage.baseMessage> {

    private static final Logger logger = LoggerFactory.getLogger(IMChannelHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BaseMessage.baseMessage baseMessage) throws Exception {
        logger.info("message received : {}. Type : {}",baseMessage,baseMessage.getClass().getName());
    }
}
