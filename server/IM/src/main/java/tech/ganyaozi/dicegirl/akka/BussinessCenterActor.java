package tech.ganyaozi.dicegirl.akka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class BussinessCenterActor extends UntypedActor {

    private final LoggingAdapter logger = Logging.getLogger(this);

    @Override
    public void onReceive(Object message) throws Exception {
        logger.info("\nBussiness center receive message : {} ", message);
    }
}
