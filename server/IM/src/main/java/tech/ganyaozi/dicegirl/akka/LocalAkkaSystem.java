package tech.ganyaozi.dicegirl.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class LocalAkkaSystem {

    private static ActorSystem _system;

    public static ActorRef boost() {
        if (_system == null) {
            _system = ActorSystem.create("IM_local_system");
        }
        // message queue
//        ActorRef messageQueueActor = _system.actorOf(Props.create(EmptyLocalActorRef.class),"message_queue");

        return _system.actorOf(Props.create(BussinessCenterActor.class), "bussiness_center");
    }
}
