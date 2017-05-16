package tech.ganyaozi.dicegirl.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

public class IMChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    protected void initChannel(NioSocketChannel clientChannel) throws Exception {
        clientChannel.pipeline()
                .addLast(new LoggingHandler(LogLevel.INFO))
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(BaseMessage.baseMessage.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(new IMClientHandler());
    }
}
