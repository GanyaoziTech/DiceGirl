package tech.ganyaozi.dicegirl.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.apache.commons.lang3.StringUtils;
import tech.ganyaozi.dicegirl.akka.business.RoomActor;
import tech.ganyaozi.dicegirl.command.IMCommand;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

import java.util.HashMap;

public class DispatcherActor extends AbstractActor {

    private final LoggingAdapter logger = Logging.getLogger(this);

    private HashMap<IMCommand.Module, ActorRef> workerMap = new HashMap<>();

    DispatcherActor() {
        workerMap.put(IMCommand.Module.DEAD_LETTER, getContext().actorFor("/deadLetter"));
        workerMap.put(IMCommand.Module.ROOM, getContext().actorOf(Props.create(RoomActor.class)));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    logger.info("Received String message: {}", s);
                })
                .match(BaseMessage.baseMessage.class, msg -> {
                    if (StringUtils.isBlank(msg.getDstID()) || StringUtils.equalsIgnoreCase(msg.getDstID(), "-1")) {
                        // this message is to server
                        workerMap.get(IMCommand.fromValue(msg.getCmd().getNumber()).getModule()).tell(msg, self());
                    } else {
                        // this message is to client

                    }
                })
                .matchAny(o -> logger.info("received unknown message"))
                .build();
    }
}
