package tech.ganyaozi.dicegirl.client;

import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

import java.util.Date;
import java.util.UUID;

public class IMClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(IMClientHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        BaseMessage.baseMessage req = (BaseMessage.baseMessage) msg;
        logger.info("Service accept client subscribe req:[" + req.toString() + "]");
        //ctx.writeAndFlush(resp(req.getSubReqID()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private BaseMessage.baseMessage subReq(int i) {
        return BaseMessage.baseMessage.newBuilder()
                .setCmd(1)
                .setUserID(UUID.randomUUID().toString())
                .setTimeStamp(new Date().getTime())
                .setContent(ByteString.copyFrom("Hello world".getBytes()))
                .build();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
