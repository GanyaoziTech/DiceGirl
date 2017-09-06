package tech.ganyaozi.dicegirl.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import tech.ganyaozi.dicegirl.netty.ClientHeartBeatHandler;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

public class IMChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    protected void initChannel(NioSocketChannel clientChannel) throws Exception {
        int ALL_IDLE_TIME_OUT = 0;
        int READ_IDLE_TIME_OUT = 0;
        int WRITE_IDLE_TIME_OUT = 10;
        clientChannel.pipeline()
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(BaseMessage.baseMessage.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(new IdleStateHandler(READ_IDLE_TIME_OUT, WRITE_IDLE_TIME_OUT, ALL_IDLE_TIME_OUT))
                .addLast(new ClientHeartBeatHandler())
                .addLast(new IMClientHandler());
    }
}
