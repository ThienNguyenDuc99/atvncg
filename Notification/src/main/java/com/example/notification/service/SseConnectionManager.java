package com.example.notification.service;

import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class SseConnectionManager {

    // userId → Channel
    // TODO: why concurrent hashmap
    private static final ConcurrentHashMap<String, Object> sessions = new ConcurrentHashMap<>();

    private static final Logger LOGGER = LogManager.getLogger(SseConnectionManager.class);

    public static void registerConnection(String traceId, Channel ch) {
        sessions.put(traceId, ch);

        long start = System.currentTimeMillis();
        ch.closeFuture().addListener(f -> {
            long duration = System.currentTimeMillis() - start;
            System.out.println("SSE closed traceId={} after {} ms: " +  traceId + ", "+ duration);
        });
    }
    public static void registerEvent(String traceId, String status) {
        sessions.put(traceId, status);
    }

    public static Object get(String traceId) {
        return sessions.get(traceId);
    }

    public static boolean isContainConnection(String traceId) {
        Channel ch = (Channel) sessions.get(traceId);
        return ch != null && ch.isActive();
    }

    public static boolean isReadyStatusConnection(String traceId) {
        String status = (String) sessions.get(traceId);
        return Objects.equals(status, "READY");
    }

    public static void remove(String traceId) {
        sessions.remove(traceId);
    }
}
