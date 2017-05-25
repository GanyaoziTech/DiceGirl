package tech.ganyaozi.dicegirl.http;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertNotNull;

public class HttpServerTest {

    private static final Logger logger = LoggerFactory.getLogger(HttpServerTest.class);

    private DiceHttpServer server;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Before
    public void before() {
        server  = new DiceHttpServer();
//        server.startServer(false,44445);
        executorService.submit(() -> server.startServer(false, 44445));
    }

    @After
    public void after() throws InterruptedException {
       server.stopServer();
    }

    @Test
    public void httpServerTest(){
        Map<String,Object> params = new HashMap<>();
        params.put("SALT","default");
        String response = HttpClientHelper.httpClientGet("http://localhost:44445/", params, "UTF-8");
        logger.info("response : {}", response);
        assertNotNull(response);
    }
}
