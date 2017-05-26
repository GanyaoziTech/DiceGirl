package tech.ganyaozi.dicegirl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ganyaozi.dicegirl.client.DiceIMClient;

import java.net.InetSocketAddress;

public class ClientReconnectTask implements Runnable {
    public static final long SLEEP_INTERVAL = 10000L;
    private static final Logger logger = LoggerFactory.getLogger(ClientReconnectTask.class);
    private InetSocketAddress address;

    public ClientReconnectTask(InetSocketAddress address) {
        this.address = address;
    }

    @Override
    public void run() {
        System.out.println("Daemon Thread start !");
        while (true) {
            try {
                Thread.sleep(SLEEP_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (DiceIMClient.channel == null) {
                continue;
            }
            logger.debug("Check connection validation : {} ", DiceIMClient.channel.isActive());
            if (!DiceIMClient.channel.isActive()) {
                logger.error("Reconnecting to server : {} ...... ", address);
                DiceIMClient.startServer(address);
            }
        }
    }
}


