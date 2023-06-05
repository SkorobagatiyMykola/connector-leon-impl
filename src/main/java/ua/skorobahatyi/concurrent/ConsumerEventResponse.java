package ua.skorobahatyi.concurrent;

import ua.skorobahatyi.models.response.EventResponse;
import ua.skorobahatyi.utils.Reporting;

import java.util.concurrent.BlockingQueue;

public class ConsumerEventResponse implements Runnable {
    private BlockingQueue<EventResponse> queueResponse;
    static Reporting report = new Reporting();
    private static final EventResponse exitEventResponse = new EventResponse(-1L, "test");

    public ConsumerEventResponse(BlockingQueue<EventResponse> queueResponse) {
        this.queueResponse = queueResponse;
    }

    @Override
    public void run() {
        try {
            while (true) {
                EventResponse response = queueResponse.take();
                if (response.equals(exitEventResponse)) {
                    break;
                }
                Thread.sleep(20);
                report.printConsole(response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
