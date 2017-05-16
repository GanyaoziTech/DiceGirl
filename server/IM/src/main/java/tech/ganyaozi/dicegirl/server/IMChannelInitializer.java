package tech.ganyaozi.dicegirl.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

public class IMChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    static int WRITE_IDLE_TIME_OUT = 5;
    static int READ_IDLE_TIME_OUT = 10;
    static int ALL_IDLE_TIME_OUT = 10;

    protected void initChannel(NioSocketChannel serverChannel) throws Exception {
        serverChannel.pipeline()
                .addLast(new ProtobufVarint32FrameDecoder())
                //与c++通信时这里的varint32要注释掉，因为默认的protobuf是没有32位对齐的，如果要实现自动分包，那么要在C++客户端进行组装
                .addLast(new ProtobufDecoder(BaseMessage.baseMessage.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(new IMObjectChannelHandler());
    }
}
