package tech.ganyaozi.dicegirl.netty.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

public class SSLChannelInitializer extends ChannelInitializer<Channel> {

    private SslContext sslContext;

    private boolean isClient;

    public SSLChannelInitializer(SslContext sslContext, boolean idClient) {
        this.sslContext = sslContext;
        this.isClient = idClient;
    }

    public SSLChannelInitializer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        SSLEngine engine = sslContext.newEngine(ch.alloc());
        ch.pipeline().addFirst("ssl",new SslHandler(engine,false));
        if (isClient) {
            ch.pipeline()
                    .addLast("decoder", new HttpResponseDecoder())
                    .addLast("encoder", new HttpRequestEncoder());
        } else {
            ch.pipeline()
                    .addLast("decoder", new HttpRequestDecoder())
                    .addLast("encoder", new HttpResponseEncoder());
        }
    }
}
