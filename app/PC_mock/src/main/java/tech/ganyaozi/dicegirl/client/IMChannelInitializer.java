package tech.ganyaozi.dicegirl.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import proto.BaseMessage;

public class IMChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    private static int WRITE_IDLE_TIME_OUT = 10;
    private static int READ_IDLE_TIME_OUT = 0;
    private static int ALL_IDLE_TIME_OUT = 0;

    protected void initChannel(NioSocketChannel clientChannel) throws Exception {
        clientChannel.pipeline()
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(BaseMessage.baseMessage.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(new IdleStateHandler(READ_IDLE_TIME_OUT,WRITE_IDLE_TIME_OUT,ALL_IDLE_TIME_OUT))
                .addLast(new IMClientHandler());
    }
}
