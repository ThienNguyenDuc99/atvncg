package com.example.notification.service;

import com.example.notification.handler.SseHttpHandler;
import com.example.notification.utils.SseEncoder;
import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class NotificationDispatcher {

    private static final Logger LOGGER = LogManager.getLogger(SseHttpHandler.class);

    public static boolean send(String traceId, String data) {

        Channel ch = (Channel) SseConnectionManager.get(traceId);

        if (ch != null && ch.isActive()) {
            LOGGER.info("send notify to FE with traceId {}", traceId);
            System.out.println("4. send notify to FE with traceId: " + traceId);
            ch.writeAndFlush(SseEncoder.event("booking-ready", data));
            return true;

        } else {
            SseConnectionManager.registerEvent(traceId, "READY");
            PendingEventStore.save(traceId, data, Duration.ofMinutes(2));
            return false;
        }
    }
}
