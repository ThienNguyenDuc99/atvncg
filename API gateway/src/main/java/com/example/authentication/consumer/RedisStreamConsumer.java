package com.example.authentication.consumer;

import com.example.authentication.dto.StreamRequestMessage;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

@Service
public class RedisStreamConsumer {

    private static final Logger LOGGER = LogManager.getLogger(RedisStreamConsumer.class);

    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${spring.data.redis.stream.key}")
    private String streamKey;

    @Value("${spring.data.redis.stream.group}")
    private String group;

    @Value("${spring.data.redis.stream.consumer}")
    private String consumer;

    public RedisStreamConsumer(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void start() {
        Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setName("redis-stream-consumer");
            t.setDaemon(true);
            return t;
        }).submit(this::listen);
    }

    private void listen() {
        LOGGER.info("Redis Stream consumer started. stream={}, group={}, consumer={}",
                streamKey, group, consumer);

        while (!Thread.currentThread().isInterrupted()) {
            try {
                List<MapRecord<String, Object, Object>> messages =
                        redisTemplate.opsForStream().read(
                                Consumer.from(group, consumer),
                                StreamReadOptions.empty()
                                        .count(10)
                                        .block(Duration.ofMillis(500)), // 🔥 BLOCK NGẮN
                                StreamOffset.create(streamKey, ReadOffset.lastConsumed())
                        );

                if (messages == null || messages.isEmpty()) {
                    // STREAM RỖNG → BÌNH THƯỜNG
                    continue;
                }

                for (MapRecord<String, Object, Object> record : messages) {
                    handleMessage(record);
                }

            } catch (org.springframework.dao.QueryTimeoutException e) {
                // ✅ timeout do BLOCK → IGNORE
            } catch (Exception e) {
                LOGGER.error("Unexpected error consuming redis stream", e);
                sleepSilently(1000);
            }
        }
    }

    private void handleMessage(MapRecord<String, Object, Object> record) {
        try {
            Map<Object, Object> value = record.getValue();

            StreamRequestMessage msg = new StreamRequestMessage();
            msg.setUserId(Long.parseLong(value.get("userId").toString()));
            msg.setUsername(value.get("username").toString());
            msg.setScheduledTime(Long.parseLong(value.get("scheduledTime").toString()));

            // 🔥 BUSINESS LOGIC
            String username = msg.getUsername();
            Long userId = msg.getUserId();
            LOGGER.info("Processing request: {} + {}", userId, username);

            // TODO: call gRPC / process request

            // ✅ ACK CHỈ KHI THÀNH CÔNG
            redisTemplate.opsForStream()
                    .acknowledge(streamKey, group, record.getId());

        } catch (Exception e) {
            LOGGER.error("Failed to process message {}", record.getId(), e);
            // ❌ không ACK → message nằm PENDING để retry
        }
    }

    private void sleepSilently(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

