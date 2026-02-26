package com.example.notification.handler;

import com.example.notification.service.SseConnectionManager;
import com.example.notification.utils.SseEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SseHttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger LOGGER = LogManager.getLogger(SseHttpHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) {

        QueryStringDecoder decoder = new QueryStringDecoder(req.uri());

        String traceId = decoder.parameters().get("traceId").get(0);
        String userId = decoder.parameters().get("userId").get(0);

        LOGGER.info("receive sse connection from FE with traceId {}", traceId);
        System.out.println("1. receive sse connection from FE with traceId: " + traceId);
        boolean isReady = SseConnectionManager.isReadyStatusConnection(traceId);
        Channel ch = ctx.channel();
        if (isReady) {
            LOGGER.info("send sse connection to FE with traceId {}", traceId);
            System.out.println("send sse connection to FE with traceId: " + traceId);

            DefaultHttpResponse response = new DefaultHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK
            );
            setResponseHeader(response);

            ctx.write(response);

            ctx.writeAndFlush(SseEncoder.event("booking-ready", userId));
//            ctx.writeAndFlush(new DefaultHttpContent(
//                    Unpooled.copiedBuffer(": connected\n\n", CharsetUtil.UTF_8)
//            ));
            ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT)
                    .addListener(ChannelFutureListener.CLOSE);


            long start = System.currentTimeMillis();
            ch.closeFuture().addListener(f -> {
                long duration = System.currentTimeMillis() - start;
                System.out.println("SSE closed traceId={} after {} ms: " +  traceId + ", "+ duration);
            });

            //TODO: remove element in connection map
        } else {
            HttpResponse response = new DefaultHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK);

            setResponseHeader(response);

            ctx.write(response);
//            ctx.writeAndFlush(new DefaultHttpContent(
//                    Unpooled.copiedBuffer(": connected\n\n", CharsetUtil.UTF_8)
//            ));
//            ctx.flush();


            LOGGER.info("register connection with traceId {}", traceId);
            System.out.println("2. register connection with traceId: " + traceId);
            SseConnectionManager.registerConnection(traceId, ch);
            long start = System.currentTimeMillis();
            ch.closeFuture().addListener(f -> {
                long duration = System.currentTimeMillis() - start;
                System.out.println("SSE closed traceId={} after {} ms: " +  traceId + ", "+ duration);
            });
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("CHANNEL INACTIVE: " + ctx.channel());
    }

    private static void setResponseHeader(HttpResponse response) {
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/event-stream");
        response.headers().set(HttpHeaderNames.CACHE_CONTROL, "no-cache");
        response.headers().set(HttpHeaderNames.CONNECTION, "keep-alive");
        response.headers().set(HttpHeaderNames.TRANSFER_ENCODING, HttpHeaderValues.CHUNKED);
        response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:5173");
        response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "GET, OPTIONS");
        response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "*");
    }
}
