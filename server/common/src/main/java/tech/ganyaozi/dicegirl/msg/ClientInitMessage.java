package tech.ganyaozi.dicegirl.msg;

import com.google.protobuf.ByteString;
import proto.BaseMessage;

import java.util.UUID;

public class ClientInitMessage {

    private ClientInitMessage() {
    }

    public static BaseMessage.baseMessage getInstance(UUID uuid) {
        return BaseMessage.baseMessage.newBuilder()
                .setCmd(Command.Client_Init.getValue())
                .setUserID(uuid.toString())
                .setTimeStamp(System.currentTimeMillis())
                .setContent(ByteString.copyFrom(new byte[0]))
                .build();

    }

}
