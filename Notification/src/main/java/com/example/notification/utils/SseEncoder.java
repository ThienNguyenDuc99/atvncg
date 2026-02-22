package com.example.notification.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class SseEncoder {

    public static ByteBuf event(String event, String data) {

        String payload =
                "event: " + event + "\n" +
                        "data: " + data + "\n\n";

        return Unpooled.copiedBuffer(payload, CharsetUtil.UTF_8);
    }
}
