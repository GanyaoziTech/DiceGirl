package tech.ganyaozi.dicegirl.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.msg.Command;
import tech.ganyaozi.dicegirl.msg.PingPongMessage;
import proto.BaseMessage;

public class IMClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(IMClientHandler.class);

    private static final AttributeKey<String> key = AttributeKey.valueOf("uuid");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        BaseMessage.baseMessage req = (BaseMessage.baseMessage) msg;
        logger.info("Service accept client subscribe req:[" + req.toString() + "]");
        // 设置本次连接的唯一ID
        if (req.getCmd() == Command.Client_Init.getValue()){
            ctx.attr(key).set(req.getUserID());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            logger.warn("Idle event : {}" , ((IdleStateEvent) evt).state());
            switch (((IdleStateEvent) evt).state()){
                case ALL_IDLE:
                case WRITER_IDLE :
                    ctx.writeAndFlush(PingPongMessage.getPingInstance(ctx.attr(key).get()));
                    break;
                case READER_IDLE:
                    break;
                default:
                    break;
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
