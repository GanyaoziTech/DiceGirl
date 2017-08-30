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
        return _system.actorOf(Props.create(DispatcherActor.class), "bussiness_center");
    }
}
