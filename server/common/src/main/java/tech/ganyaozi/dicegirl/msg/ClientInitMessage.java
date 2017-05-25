package tech.ganyaozi.dicegirl.msg;

import com.google.protobuf.ByteString;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

import java.util.UUID;

public class ClientInitMessage {

    private ClientInitMessage() {
    }

    public static BaseMessage.baseMessage getInstance(UUID uuid) {
        return BaseMessage.baseMessage.newBuilder()
                .setCmd(BaseMessage.Commands.IM_SECURE_KEY)
                .setAck(false)
                .setDstID(uuid.toString())
                .setSrcID("")
                .setContent(ByteString.copyFrom(new byte[0]))
                .build();
    }

}
