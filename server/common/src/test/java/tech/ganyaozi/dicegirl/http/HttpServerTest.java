package tech.ganyaozi.dicegirl.http;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerTest {

    private DiceHttpServer server;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Before
    public void before() {
        server  = new DiceHttpServer();
        server.startServer(false,44445);
//        executorService.submit(() -> server.startServer(false,44445));
    }

    @After
    public void after() throws InterruptedException {
       server.stopServer();
    }

    @Test
    public void httpServerTest(){
        Map<String,Object> params = new HashMap<>();
        params.put("SALT","default");
        HttpClientHelper.httpClientGet("http://localhost:44445",params,"UTF-8");
    }
}
