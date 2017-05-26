package tech.ganyaozi.dicegirl.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class LocalAkkaSystem {

    private static ActorSystem _system;

    public static ActorRef boost() {
        if (_system == null) {
            _system = ActorSystem.create("IM_local_system",
                    ConfigFactory.parseString("loggers = [\"akka.event.slf4j.Slf4jLogger\"]")
                            .withFallback(ConfigFactory.parseString("loglevel = \"INFO\""))
                            .withFallback(ConfigFactory.parseString("actor.provider = \"akka.cluster.ClusterActorRefProvider\""))
                            .withFallback(ConfigFactory.parseString("actor.default-dispatcher.throughput = 1"))
                            .withFallback(ConfigFactory.parseString("remote.netty.tcp.port=44445"))
                            .withFallback(ConfigFactory.load("application")));
        }
        // message queue
//        ActorRef messageQueueActor = _system.actorOf(Props.create(EmptyLocalActorRef.class),"message_queue");

        return _system.actorOf(Props.create(BussinessCenterActor.class), "bussiness_center");
    }
}
