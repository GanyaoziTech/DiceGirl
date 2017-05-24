package tech.ganyaozi.dicegirl.server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.AttributeKey;
import proto.BaseMessage;

public class IMChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    public final AttributeKey<String> ids = AttributeKey.valueOf("ID");

    private static int WRITE_IDLE_TIME_OUT = 0;
    private static int READ_IDLE_TIME_OUT = 20;
    private static int ALL_IDLE_TIME_OUT = 0;

    protected void initChannel(NioSocketChannel serverChannel) throws Exception {
        serverChannel.pipeline()
                .addLast(new ProtobufVarint32FrameDecoder())
                //与c++通信时这里的varint32要注释掉，因为默认的protobuf是没有32位对齐的，如果要实现自动分包，那么要在C++客户端进行组装
                .addLast(new ProtobufDecoder(BaseMessage.baseMessage.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(new IdleStateHandler(READ_IDLE_TIME_OUT,WRITE_IDLE_TIME_OUT,ALL_IDLE_TIME_OUT))
                .addLast(new IMChannelHandler());
    }
}
