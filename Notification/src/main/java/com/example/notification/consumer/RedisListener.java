package com.example.notification.consumer;

import com.example.notification.handler.SseHttpHandler;
import com.example.notification.service.NotificationDispatcher;
import io.lettuce.core.XReadArgs;
import io.lettuce.core.XReadArgs.StreamOffset;
import io.lettuce.core.StreamMessage;
import io.lettuce.core.Consumer;
import io.lettuce.core.api.sync.RedisCommands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.List;

public class RedisListener implements Runnable {

    private final RedisCommands<String, String> redis;
    private final String consumerName;
    private volatile boolean running = true;

    private static final String STREAM = "request_stream";
    private static final String GROUP = "notification_group";

    private static final Logger LOGGER = LogManager.getLogger(SseHttpHandler.class);

    public RedisListener(RedisCommands<String, String> redis, String consumerName) {
        this.redis = redis;
        this.consumerName = consumerName;
    }

    @Override
    public void run() {

        while (running) {
            try {

                List<StreamMessage<String, String>> messages =
                        redis.xreadgroup(
                                Consumer.from(GROUP, consumerName),
                                XReadArgs.Builder.block(Duration.ofSeconds(30)).count(10),
                                StreamOffset.from(STREAM, ">")
                        );

                if (messages == null || messages.isEmpty()) {
                    continue;
                }

                for (StreamMessage<String, String> msg : messages) {
                    String traceId = msg.getBody().get("traceId");
                    String payload = msg.getBody().get("userId");

                    LOGGER.info("receive deque message from schedule service with traceId {}", traceId);
                    System.out.println("3. receive deque message from schedule service with traceId: " + traceId);
                    boolean success = NotificationDispatcher.send(traceId, payload);

                    if (success) {
                        redis.xack(STREAM, GROUP, msg.getId());
                    } else {
                        // optional: log retry
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}
