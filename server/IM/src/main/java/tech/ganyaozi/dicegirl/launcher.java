package tech.ganyaozi.dicegirl;

import akka.actor.ActorRef;
import ch.qos.logback.core.joran.spi.JoranException;
import tech.ganyaozi.dicegirl.akka.LocalAkkaSystem;
import tech.ganyaozi.dicegirl.server.netty.DiceIMServer;

import java.io.IOException;

import static tech.ganyaozi.dicegirl.utils.LogbackConfiguration.loadLogbackConfig;

public class launcher {

    public static final String LOGBACK_CONFIG_PATH = "config/logback.xml";

    public static void main(String[] args) throws IOException, JoranException {

        ActorRef bussinessCenter = LocalAkkaSystem.boost();

        loadLogbackConfig(LOGBACK_CONFIG_PATH);

        DiceIMServer.init(44444, bussinessCenter);
    }

}
