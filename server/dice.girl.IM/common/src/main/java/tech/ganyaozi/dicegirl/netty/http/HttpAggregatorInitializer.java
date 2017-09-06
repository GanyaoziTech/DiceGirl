package tech.ganyaozi.dicegirl.netty.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {

    public static final int HTTP_MAX_LENGTH = 512 * 1024;   // 最大允许的http报文长度，默认 512k

    private boolean isClient;

    public HttpAggregatorInitializer(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        if (isClient) {
            ch.pipeline().addLast("codec", new HttpClientCodec());
        } else {
            ch.pipeline().addLast("codec", new HttpServerCodec());
        }
        ch.pipeline().addLast("aggregator", new HttpObjectAggregator(HTTP_MAX_LENGTH))
                .addLast("IMHandler", new IMHttpHandler());

    }
}
