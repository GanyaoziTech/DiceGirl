package tech.ganyaozi.dicegirl;

import com.google.protobuf.ByteString;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class EncodeTest {

    private static final Logger logger = LoggerFactory.getLogger(EncodeTest.class);
    private EmbeddedChannel channel;
    private ChannelHandler pvfDecoder = new ProtobufVarint32FrameDecoder();
    private ChannelHandler decoder = new ProtobufDecoder(BaseMessage.baseMessage.getDefaultInstance());
    private ChannelHandler encoder = new ProtobufEncoder();
    //    private ChannelHandler idleStateHandler = new IdleStateHandler(IMChannelInitializer.READ_IDLE_TIME_OUT, IMChannelInitializer.WRITE_IDLE_TIME_OUT, IMChannelInitializer.ALL_IDLE_TIME_OUT, TimeUnit.MINUTES);
//    private ChannelHandler iMHandler = new IMObjectChannelHandler();

    @Before
    public void before() {
        channel = new EmbeddedChannel(pvfDecoder, decoder, encoder);
    }

    @Test
    public void protobufMessageDecodeTest() {
        BaseMessage.baseMessage message = BaseMessage.baseMessage.newBuilder()
                .setCmd(1)
                .setUserID(UUID.randomUUID().toString())
                .setTimeStamp(new Date().getTime())
                .setContent(ByteString.copyFrom("Hello world".getBytes()))
                .build();

        // write message
        assertTrue(channel.writeInbound(Unpooled.wrappedBuffer(message.toByteArray())));
        assertTrue(channel.finish());

        logger.info("{}", (Object[]) channel.readInbound());

        //read message


    }

}
