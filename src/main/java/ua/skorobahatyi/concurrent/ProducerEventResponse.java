package ua.skorobahatyi.concurrent;

import ua.skorobahatyi.connector.LeonConnector;
import ua.skorobahatyi.models.response.EventResponse;

import java.util.concurrent.BlockingQueue;

public class ProducerEventResponse implements Runnable {
    private BlockingQueue<Long> queueEvents;
    private BlockingQueue<EventResponse> queueResponse;
    private LeonConnector leonConnector = new LeonConnector();
    private static final Long exitEvent = Long.MAX_VALUE;
    private static final EventResponse exitEventResponse = new EventResponse(Long.MAX_VALUE, "test");

    public ProducerEventResponse(BlockingQueue<Long> queueEvents, BlockingQueue<EventResponse> queueResponse) {
        this.queueEvents = queueEvents;
        this.queueResponse = queueResponse;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Long eventId = queueEvents.take();
                if (eventId.equals(exitEvent)) {
                    //adding exit eventResponse
                    queueResponse.put(exitEventResponse);
                    break;
                } else {
                    Thread.sleep(10);
                    EventResponse response = leonConnector.getAllMarketForEvent(eventId);
                    queueResponse.put(response);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
