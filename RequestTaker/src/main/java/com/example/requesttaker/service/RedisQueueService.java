package com.example.requesttaker.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisQueueService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisQueueService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final String QUEUE_KEY = "request_queue";

    public void addToQueue(String member) {
        long score = System.currentTimeMillis();

        redisTemplate.opsForZSet()
                .add(QUEUE_KEY, member, score);
    }

    public long getRank(String member) {
        Long rank = redisTemplate.opsForZSet()
                .rank(QUEUE_KEY, member); // ZRANK

        long position = rank != null ? rank + 1 : -1;
        return position;
    }
}