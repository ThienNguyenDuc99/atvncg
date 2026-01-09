package com.example.requesttaker.service;

import com.example.requesttaker.dto.RedisQueueObj;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisQueueService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisQueueService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final String QUEUE_KEY = "request_queue";

    public void addToQueue(RedisQueueObj obj) {
        long score = System.currentTimeMillis();

        redisTemplate.opsForZSet()
                .add(QUEUE_KEY, obj, score);
    }
}