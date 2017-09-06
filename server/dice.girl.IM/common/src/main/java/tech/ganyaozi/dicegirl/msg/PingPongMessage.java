package tech.ganyaozi.dicegirl.msg;

import com.google.protobuf.ByteString;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

public class PingPongMessage {

    private PingPongMessage() {
    }

    public static BaseMessage.baseMessage getPingInstance(String userId) {
        return BaseMessage.baseMessage.newBuilder()
                .setCmd(BaseMessage.Commands.IM_PING_PONG)
                .setContent(ByteString.copyFrom(new byte[0]))
                .setSrcID(userId)
                .setAck(false)
                .build();
    }

    public static BaseMessage.baseMessage getPongInstance(String userId) {
        return BaseMessage.baseMessage.newBuilder()
                .setCmd(BaseMessage.Commands.IM_PING_PONG)
                .setContent(ByteString.copyFrom(new byte[0]))
                .setDstID(userId)
                .setAck(true)
                .build();
    }

}
