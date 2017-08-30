package tech.ganyaozi.dicegirl.akka.business;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import tech.ganyaozi.dicegirl.proto.BaseMessage;

/**
 * @author Derek.P.Dai[derek.dai@corp.netease.com]
 * @date 2017/8/30 15:53
 **/
public class RoomActor extends AbstractActor {

    private final LoggingAdapter logger = Logging.getLogger(this);

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(BaseMessage.baseMessage.class, msg -> {
                    logger.info("{}", msg);
                    switch (msg.getCmd()) {
                        case IM_CREATE_ROOM_REQ:
                            createRoom(msg);
                            break;
                        case IM_ENTER_ROOM_REQ:
                            enterRoom(msg);
                            break;
                        case IM_EXIT_ROOM_REQ:
                            exitRoom(msg);
                            break;
                        case IM_UPDATE_ROOM_INFO_REQ:
                            updateRoomInfo(msg);
                            break;
                        default:
                            logger.error("Unhandled message : {} ", msg);
                    }
                }).matchAny(o -> logger.info("received unknown message {}", o))
                .build();
    }

    private void updateRoomInfo(BaseMessage.baseMessage msg) {
    }

    private void exitRoom(BaseMessage.baseMessage msg) {
    }

    private void enterRoom(BaseMessage.baseMessage msg) {
    }

    private void createRoom(BaseMessage.baseMessage msg) {
        logger.info("start to handle create room req");
    }
}
