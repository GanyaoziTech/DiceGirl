package tech.ganyaozi.dicegirl.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proto.BaseMessage;
import tech.ganyaozi.dicegirl.msg.Command;
import tech.ganyaozi.dicegirl.msg.PingPongMessage;

public class IMClientHandler extends SimpleChannelInboundHandler<BaseMessage.baseMessage> {

    private static final Logger logger = LoggerFactory.getLogger(IMClientHandler.class);

    private static final AttributeKey<String> key = AttributeKey.valueOf("uuid");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMessage.baseMessage req) throws Exception {
        logger.info("Service accept client subscribe req:[" + req.toString() + "]");
        // 设置本次连接的唯一ID
        if (req.getCmd() == Command.Client_Init.getValue()){
            ctx.channel().attr(key).set(req.getUserID());
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
