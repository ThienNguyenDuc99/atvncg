package com.example.notification.handler;

import com.example.notification.service.PendingEventStore;
import com.example.notification.service.SseConnectionManager;
import com.example.notification.utils.SseEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SseHttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger LOGGER = LogManager.getLogger(SseHttpHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) {

        QueryStringDecoder decoder = new QueryStringDecoder(req.uri());

        String traceId = decoder.parameters().get("traceId").get(0);
        String userId = decoder.parameters().get("userId").get(0);

        // TODO: why dont get on concurrent hashmap???
        LOGGER.info("receive sse connection from FE with traceId {}", traceId);
        System.out.println("receive sse connection from FE with traceId: " + traceId);
//        String pending = PendingEventStore.pop(traceId);
        boolean isReady = SseConnectionManager.isReadyStatusConnection(traceId);
//        boolean isConnectionExist = SseConnectionManager.isContainConnection(traceId);
        Channel ch = ctx.channel();
        if (isReady) {
            LOGGER.info("send sse connection to FE with traceId {}", traceId);
            System.out.println("send sse connection to FE with traceId: " + traceId);
            ch.writeAndFlush(
                    SseEncoder.event("booking-ready", userId)
            );
        } else {
            HttpResponse response = new DefaultHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/event-stream");
            response.headers().set(HttpHeaderNames.CACHE_CONTROL, "no-cache");
            response.headers().set(HttpHeaderNames.CONNECTION, "keep-alive");

            ctx.writeAndFlush(response);

            SseConnectionManager.registerConnection(traceId, ctx.channel(), userId);
        }
    }
}
