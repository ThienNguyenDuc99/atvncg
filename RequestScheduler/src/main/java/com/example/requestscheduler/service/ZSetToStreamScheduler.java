package com.example.requestscheduler.service;

import com.example.requestscheduler.dto.RedisQueueObj;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ZSetToStreamScheduler {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final Logger LOGGER = LogManager.getLogger(ZSetToStreamScheduler.class);

    private final ObjectMapper objectMapper;

    @Value("${scheduler.zset-key}")
    private String zsetKey;

    @Value("${scheduler.stream-key}")
    private String streamKey;

    private static final int BATCH_SIZE = 200;

    /**
     * Chạy mỗi 15 giây
     */
    @Scheduled(fixedDelay = 20000)
    public void pollZSetAndPublishStream() {

        long now = System.currentTimeMillis();

        // 1️⃣ Lấy 100 phần tử đã đến hạn
        Set<ZSetOperations.TypedTuple<Object>> items =
                redisTemplate.opsForZSet()
                        .rangeByScoreWithScores(
                                zsetKey,
                                0,
                                now,
                                0,
                                BATCH_SIZE
                        );

        if (items == null || items.isEmpty()) {
            return;
        }

        log.info("Found {} scheduled requests", items.size());

        for (ZSetOperations.TypedTuple<Object> item : items) {

            Object rawValue = item.getValue();
            double score = item.getScore();
            String member;
            String userId;
            String eventId;
            String traceId;

            if (rawValue instanceof String) {
                member = rawValue.toString();
                String[] parts = member.split(":", 3); // limit = 3 để traceId giữ nguyên
                userId = parts[0];
                eventId = parts[1];
                traceId = parts[2];
            }  else {
                log.warn("Unsupported redis value type: {}", rawValue.getClass());
                continue;
            }

            Map<String, String> body = new HashMap<>();
            body.put("userId", userId);
            body.put("eventId", eventId);
            body.put("traceId", traceId);
            body.put("scheduledTime", String.valueOf((long) score));

            LOGGER.info("Send to Request Taker message with: {}", member);

            redisTemplate.opsForStream().add(
                    StreamRecords.mapBacked(body).withStreamKey(streamKey)
            );
        }


        // 3️⃣ Xoá khỏi ZSET sau khi push
        redisTemplate.opsForZSet().remove(
                zsetKey,
                items.stream().map(ZSetOperations.TypedTuple::getValue).toArray()
        );

        log.info("Published to stream and removed from zset");
    }
}