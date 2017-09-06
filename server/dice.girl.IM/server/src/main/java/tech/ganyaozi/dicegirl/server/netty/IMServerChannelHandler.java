package tech.ganyaozi.dicegirl.server.netty;

import akka.actor.ActorRef;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

public class IMServerChannelHandler extends SimpleChannelInboundHandler<BaseMessage.baseMessage> {

    private static final Logger logger = LoggerFactory.getLogger(IMServerChannelHandler.class);

    private final AttributeKey<String> key = AttributeKey.valueOf("uuid");

    private ActorRef bussinessCenter;

    public IMServerChannelHandler(ActorRef bussinessCenter) {
        this.bussinessCenter = bussinessCenter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMessage.baseMessage msg) throws Exception {
        bussinessCenter.tell(msg, ActorRef.noSender());
    }
}
