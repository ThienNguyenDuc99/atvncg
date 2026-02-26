package com.example.notification.service;

import com.example.notification.handler.SseHttpHandler;
import com.example.notification.utils.SseEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class NotificationDispatcher {

    private static final Logger LOGGER = LogManager.getLogger(SseHttpHandler.class);

    public static boolean send(String traceId, String data) {

        Channel ch = (Channel) SseConnectionManager.get(traceId);

        if (ch != null && ch.isActive()) {
            LOGGER.info("send sse connection to FE with traceId {}", traceId);
            System.out.println("send sse connection to FE with traceId: " + traceId);

            String payload = "data: " + data + "\n\n";
            ByteBuf buf = Unpooled.copiedBuffer(payload, CharsetUtil.UTF_8);

            ch.writeAndFlush(buf);

            //TODO: remove element in connection map

            return true;
        } else {
            SseConnectionManager.registerEvent(traceId, "READY");
            PendingEventStore.save(traceId, data, Duration.ofMinutes(2));
            return false;
        }
    }

    public static ByteBuf event(String data) {
        String payload = "data: " + data + "\n\n";
        return Unpooled.copiedBuffer(payload, CharsetUtil.UTF_8);
    }
}
