package com.example.notification.service;

import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;

public class PendingEventStore {

    private static RedisCommands<String, String> redis;

    public static void init(RedisCommands<String, String> commands) {
        redis = commands;
    }

    private static String key(String traceId) {
        return "pending:sse:" + traceId;
    }

    public static void save(String traceId, String payload, Duration ttl) {
        redis.setex(key(traceId), ttl.toSeconds(), payload);
    }

    public static String pop(String traceId) {
        String k = key(traceId);
        String value = redis.get(k);
        if (value != null) {
            redis.del(k);
        }
        return value;
    }
}
