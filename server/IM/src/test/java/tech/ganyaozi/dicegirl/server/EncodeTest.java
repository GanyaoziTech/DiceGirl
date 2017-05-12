package tech.ganyaozi.dicegirl.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import org.junit.Before;
import org.junit.Test;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class EncodeTest {

    EmbeddedChannel channel;

    @Before
    public void before(){
        channel = new EmbeddedChannel(new ProtobufDecoder(BaseMessage.baseMessage.getDefaultInstance()));
    }

    @Test
    public void protobufMessageDecodeTest(){
        BaseMessage.baseMessage message = BaseMessage.baseMessage.newBuilder()
                .setId(1)
                .setTimeStamp(new Date().toString())
                .setContent("Hello World")
                .build();

        ByteBuf input= Unpooled.buffer();
        input.writeBytes(message.toByteArray());

        // write message
        assertTrue(channel.writeInbound(input));
        assertTrue(channel.finish());


        //read message


    }

}
