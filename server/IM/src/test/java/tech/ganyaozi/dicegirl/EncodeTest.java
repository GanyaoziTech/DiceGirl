package tech.ganyaozi.dicegirl;

import com.google.protobuf.ByteString;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class EncodeTest {
    private static final Logger logger = LoggerFactory.getLogger(EncodeTest.class);
    private EmbeddedChannel channel;
    private ChannelHandler decoder = new ProtobufDecoder(BaseMessage.baseMessage.getDefaultInstance());
    private ChannelHandler encoder = new ProtobufEncoder();
//    private ChannelHandler iMHandler = new IMServerChannelHandler();

    @Before
    public void before() {
//        channel = new EmbeddedChannel(decoder, encoder, iMHandler);
    }

    @Test
    public void protobufMessageDecodeTest() {
        BaseMessage.baseMessage message = BaseMessage.baseMessage.newBuilder()
                .setCmd(BaseMessage.Commands.IM_CREATE_ROOM_REQ)
                .setAck(false)
                .setDstID("-1")
                .setSrcID(UUID.randomUUID().toString())
                .setContent(ByteString.copyFrom(new byte[0]))
                .build();

        // write message
        assertTrue(channel.writeInbound(Unpooled.wrappedBuffer(message.toByteArray())));
        assertTrue(channel.finish());

        logger.info("{}", (Object[]) channel.readInbound());
        //read message
    }

}
