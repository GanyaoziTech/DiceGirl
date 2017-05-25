package tech.ganyaozi.dicegirl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.client.DiceIMClient;

import java.net.InetSocketAddress;

public class ClientReconnectTask implements Runnable {

    public static final long SLEEP_INTERVAL = 5000L;
    private static final Logger logger = LoggerFactory.getLogger(ClientReconnectTask.class);
    private DiceIMClient client;
    private InetSocketAddress address;

    public ClientReconnectTask(DiceIMClient client, InetSocketAddress address) {
        this.client = client;
        this.address = address;
    }

    @Override
    public void run() {
        System.out.println("Daemon Thread start !");
        while (true) {
            logger.info("Check connection validation : {} ", client.channel.isActive());
            if (!client.channel.isActive()) {
                logger.error("Reconnect to server : {} ", address);
                client.connect(address);
            }
            try {
                Thread.sleep(SLEEP_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
