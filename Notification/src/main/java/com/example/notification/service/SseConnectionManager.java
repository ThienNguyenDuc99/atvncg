package com.example.notification.service;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import io.netty.channel.Channel;

public class SseConnectionManager {

    // userId → Channel
    // TODO: why concurrent hashmap
    private static final ConcurrentHashMap<String, Object> sessions = new ConcurrentHashMap<>();

    public static void registerConnection(String traceId, Channel ch, String userId) {
        sessions.put(traceId, ch);
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
