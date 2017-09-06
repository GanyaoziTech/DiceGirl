package tech.ganyaozi.dicegirl.netty.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class HttpPipelineInitializer extends ChannelInitializer<Channel> {

    private boolean isClient;

    public HttpPipelineInitializer(boolean isClient){
        this.isClient=isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        if (isClient){
            ch.pipeline()
                    .addLast("decoder",new HttpResponseDecoder())
                    .addLast("encoder",new HttpRequestEncoder());
        }else{
            ch.pipeline()
                    .addLast("decoder",new HttpRequestDecoder())
                    .addLast("encoder",new HttpResponseEncoder());
        }
    }
}
