package com.example.notification.service;

import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SseSessionMonitor {

    private static final Logger LOGGER = LogManager.getLogger(SseSessionMonitor.class);

    private static final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    @SuppressWarnings("unchecked")
    private static ConcurrentHashMap<String, Object> getSessionsMap() {
        try {
            Field field = SseConnectionManager.class.getDeclaredField("sessions");
            field.setAccessible(true);
            return (ConcurrentHashMap<String, Object>) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException("Cannot access sessions map", e);
        }
    }

    public static void start() {

        scheduler.scheduleAtFixedRate(() -> {
            try {

                ConcurrentHashMap<String, Object> sessions = getSessionsMap();

                int total = sessions.size();
                int channelCount = 0;
                int readyCount = 0;
                int notReadyCount = 0;

                for (Map.Entry<String, Object> entry : sessions.entrySet()) {

                    Object value = entry.getValue();

                    if (value instanceof Channel) {
                        Channel ch = (Channel) value;
                        if (ch.isActive()) {
                            channelCount++;
                        }
                    }

                    if (value instanceof String) {
                        if ("READY".equals(value)) {
                            readyCount++;
                        } else {
                            notReadyCount++;
                        }
                    }
                }

                LOGGER.info("===== SSE SESSION STATS =====");
                LOGGER.info("Total entries        : {}", total);
                LOGGER.info("Active channels      : {}", channelCount);
                LOGGER.info("READY entries        : {}", readyCount);
                LOGGER.info("NOT READY entries    : {}", notReadyCount);
                LOGGER.info("==============================");

                System.out.println("===== SSE SESSION STATS =====");
                System.out.println("Total entries        :" + total);
                System.out.println("Active channels      :" + channelCount);
                System.out.println("READY entries        :" + readyCount);
                System.out.println("NOT READY entries    :" + notReadyCount);
                System.out.println("==============================");

            } catch (Exception e) {
                LOGGER.error("Error while checking SSE sessions", e);
            }

        }, 0, 30, TimeUnit.SECONDS);
    }
}