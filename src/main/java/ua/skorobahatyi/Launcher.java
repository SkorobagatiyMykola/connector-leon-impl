package ua.skorobahatyi;

import ua.skorobahatyi.concurrent.ConsumerEventResponse;
import ua.skorobahatyi.concurrent.ProducerEventId;
import ua.skorobahatyi.concurrent.ProducerEventResponse;
import ua.skorobahatyi.connector.LeonConnector;
import ua.skorobahatyi.models.response.EventResponse;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Launcher {
    private static LeonConnector leonConnector = new LeonConnector();

    public static void main(String[] args) throws Exception {

        List<Long> topIdLeagues = leonConnector.getAllTopLeaguesId();
        int size = topIdLeagues.size() + 1;

        BlockingQueue<Long> queueEvents = new ArrayBlockingQueue<>(size);
        BlockingQueue<EventResponse> queueResponse = new ArrayBlockingQueue<>(size);

        new Thread(new ProducerEventId(topIdLeagues, queueEvents)).start();
        new Thread(new ProducerEventResponse(queueEvents, queueResponse)).start();
        new Thread(new ConsumerEventResponse(queueResponse)).start();

    }
}
