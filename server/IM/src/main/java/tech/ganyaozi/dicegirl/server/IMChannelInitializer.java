package tech.ganyaozi.dicegirl.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ServerChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

import java.util.concurrent.TimeUnit;

public class IMChannelInitializer extends ChannelInitializer<ServerChannel> {

    static int WRITE_IDLE_TIME_OUT = 5;
    static int READ_IDLE_TIME_OUT = 10;
    static int ALL_IDLE_TIME_OUT = 10;

    protected void initChannel(ServerChannel serverChannel) throws Exception {
        serverChannel.pipeline()
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(BaseMessage.baseMessage.getDefaultInstance()))
                .addLast(new ProtobufEncoder())
                .addLast(new IdleStateHandler(READ_IDLE_TIME_OUT, WRITE_IDLE_TIME_OUT, ALL_IDLE_TIME_OUT, TimeUnit.MINUTES))
                .addLast(new IMChannelHandler());
    }
}
