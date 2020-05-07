package org.dreamexposure.discal.client.network.google;

import org.dreamexposure.discal.core.object.network.google.Poll;

import java.util.Timer;
import java.util.TimerTask;

import reactor.core.scheduler.Schedulers;

/**
 * Created by Nova Fox on 11/10/17.
 * Website: www.cloudcraftgaming.com
 * For Project: DisCal-Discord-Bot
 */
public class PollManager {
    static {
        instance = new PollManager();
    }

    private final static PollManager instance;

    private final Timer timer;

    //Prevent initialization.
    private PollManager() {
        //Use daemon because this is a util timer and there is no reason to keep the program running when this is
        //polling Google, just assume it timed out and re-auth if all else fails.
        timer = new Timer(true);
    }

    public static PollManager getManager() {
        return instance;
    }

    //Timer methods.
    void scheduleNextPoll(Poll poll) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                poll.setRemainingSeconds(poll.getRemainingSeconds() - poll.getInterval());
                GoogleExternalAuth.getAuth().pollForAuth(poll)
                    .subscribeOn(Schedulers.immediate())
                    .subscribe();
            }
        }, 1000 * poll.getInterval());
    }
}