package tech.ganyaozi.dicegirl.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ServerChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

import java.util.concurrent.TimeUnit;

public class IMChannelInitializer extends ChannelInitializer<ServerChannel> {

    static int WRITE_IDLE_TIME_OUT = 5;
    static int READ_IDLE_TIME_OUT = 10;
    static int ALL_IDLE_TIME_OUT = 10;

    protected void initChannel(ServerChannel serverChannel) throws Exception {
        serverChannel.pipeline()
                // Decoders
                .addLast("frameDecoder",
                        new LengthFieldBasedFrameDecoder
                                (1048576, 0, 4, 0, 4))
                .addLast("protobufDecoder", new ProtobufDecoder(BaseMessage.baseMessage.getDefaultInstance()))
                // Encoder
                .addLast("frameEncoder", new LengthFieldPrepender(4))
                .addLast("protobufEncoder", new ProtobufEncoder())
                .addLast(new IdleStateHandler(READ_IDLE_TIME_OUT, WRITE_IDLE_TIME_OUT, ALL_IDLE_TIME_OUT, TimeUnit.MINUTES))
                .addLast(new IMProtoBufHandler())
                .addLast(new IMObjectChannelHandler())
                .addLast(new LoggingHandler(LogLevel.DEBUG));
    }
}
