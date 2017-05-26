package tech.ganyaozi.dicegirl.server.netty;

import akka.actor.ActorRef;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import tech.ganyaozi.dicegirl.netty.ServerHeartBeatHandler;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

public class IMNioChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    private static int WRITE_IDLE_TIME_OUT = 0;
    private static int READ_IDLE_TIME_OUT = 20;
    private static int ALL_IDLE_TIME_OUT = 0;
    private ActorRef bussinessCenter;

    public IMNioChannelInitializer(ActorRef bussinessCenter) {
        this.bussinessCenter = bussinessCenter;
    }

    protected void initChannel(NioSocketChannel serverChannel) throws Exception {
        serverChannel.pipeline()
                .addLast("frameDecoder", new ProtobufVarint32FrameDecoder())
                //与c++通信时这里的varint32要注释掉，因为默认的protobuf是没有32位对齐的，如果要实现自动分包，那么要在C++客户端进行组装
                .addLast("protobufDecoder", new ProtobufDecoder(BaseMessage.baseMessage.getDefaultInstance()))
                .addLast("protobufPrepender", new ProtobufVarint32LengthFieldPrepender())
                .addLast("protobufEncoder", new ProtobufEncoder())
                .addLast("idleState", new IdleStateHandler(READ_IDLE_TIME_OUT, WRITE_IDLE_TIME_OUT, ALL_IDLE_TIME_OUT))
                .addLast("heartbeat", new ServerHeartBeatHandler())
                .addLast("bussiness", new IMServerChannelHandler(bussinessCenter));
    }
}
