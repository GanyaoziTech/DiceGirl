package tech.ganyaozi.dicegirl.msg;

import com.google.protobuf.ByteString;
import proto.BaseMessage;

import java.util.Date;

public class PingPongMessage {

    private PingPongMessage() {
    }

    public static BaseMessage.baseMessage getPingInstance(String userId) {
        return BaseMessage.baseMessage.newBuilder()
                .setCmd(Command.Ping.getValue())
                .setTimeStamp(new Date().getTime())
                .setContent(ByteString.copyFrom(new byte[0]))
                .setUserID(userId)
                .build();
    }

}
