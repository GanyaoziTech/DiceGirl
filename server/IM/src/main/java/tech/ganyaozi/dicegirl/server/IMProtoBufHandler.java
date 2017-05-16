package tech.ganyaozi.dicegirl.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

public class IMProtoBufHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(IMProtoBufHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        BaseMessage.baseMessage req = (BaseMessage.baseMessage) msg;
        logger.info("Service accept client subscribe req:[" + req.toString() + "]");
        //ctx.writeAndFlush(resp(req.getSubReqID()));
    }
}
