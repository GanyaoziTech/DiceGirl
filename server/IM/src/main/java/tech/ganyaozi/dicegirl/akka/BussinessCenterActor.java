package tech.ganyaozi.dicegirl.akka;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

public class BussinessCenterActor extends AbstractActor {

    private final LoggingAdapter logger = Logging.getLogger(this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    logger.info("Received String message: {}", s);
                })
                .match(BaseMessage.baseMessage.class, msg -> {
                    logger.info("{}", msg);
                })
                .matchAny(o -> logger.info("received unknown message"))
                .build();
    }
}
