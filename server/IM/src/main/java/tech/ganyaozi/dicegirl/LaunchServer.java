package tech.ganyaozi.dicegirl;

import akka.actor.ActorRef;
import ch.qos.logback.core.joran.spi.JoranException;
import tech.ganyaozi.dicegirl.akka.LocalAkkaSystem;
import tech.ganyaozi.dicegirl.server.netty.DiceIMServer;

import java.io.IOException;

import static tech.ganyaozi.dicegirl.utils.LogbackConfiguration.loadLogbackConfig;

public class LaunchServer {

    private static final String LOGBACK_CONFIG_PATH = "config/logback.xml";

    private static final int DEFAULT_PORT = 55555;

    public static void main(String[] args) throws IOException, JoranException {
        ActorRef bussinessCenter = LocalAkkaSystem.boost();
        loadLogbackConfig(LOGBACK_CONFIG_PATH);
        DiceIMServer.init(DEFAULT_PORT, bussinessCenter);

    }

}
