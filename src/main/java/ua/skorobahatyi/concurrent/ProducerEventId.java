package ua.skorobahatyi.concurrent;

import ua.skorobahatyi.connector.LeonConnector;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ProducerEventId implements Runnable {
    private List<Long> topLeagues;
    private BlockingQueue<Long> queueEvents;
    private LeonConnector leonConnector = new LeonConnector();
    private static final Long exitEvent = Long.MIN_VALUE;

    public ProducerEventId(List<Long> topLeagues, BlockingQueue<Long> queueEvents) {
        this.topLeagues = topLeagues;
        this.queueEvents = queueEvents;
    }

    @Override
    public void run() {
        for (Long leagueId : topLeagues) {
            try {
                Long eventId = leonConnector.getFirstEventIdFromLeague(leagueId);
                queueEvents.put(eventId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        //adding exit event
        try {
            queueEvents.put(exitEvent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
