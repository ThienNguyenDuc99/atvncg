package com.example.notification.service;

import com.example.notification.utils.SseEncoder;
import io.netty.channel.Channel;

import java.time.Duration;

public class NotificationDispatcher {

    public static boolean send(String traceId, String data) {

        Channel ch = (Channel) SseConnectionManager.get(traceId);

        if (ch != null && ch.isActive()) {
            ch.writeAndFlush(SseEncoder.event("booking-ready", data));
            return true;

        } else {
            SseConnectionManager.registerEvent(traceId, "READY");
            PendingEventStore.save(traceId, data, Duration.ofMinutes(2));
            return false;
        }
    }
}
