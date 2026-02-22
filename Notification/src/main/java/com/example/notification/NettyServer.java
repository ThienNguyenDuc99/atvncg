package com.example.notification;

import com.example.notification.consumer.RedisListener;
import com.example.notification.handler.SseHttpHandler;
import com.example.notification.service.PendingEventStore;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class NettyServer {

    public static void main(String[] args) throws Exception {

        // ===== Redis setup =====
        RedisClient client = RedisClient.create("redis://192.168.23.130:6379");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> redis = connection.sync();
        PendingEventStore.init(connection.sync());

        RedisListener listener = new RedisListener(redis, "consumer-1");

        Thread redisThread = new Thread(listener, "redis-listener");
        redisThread.start();

        // ===== Netty server =====
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();

        b.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {

                        ChannelPipeline p = ch.pipeline();

                        p.addLast(new HttpServerCodec());
                        p.addLast(new HttpObjectAggregator(65536));
                        p.addLast(new SseHttpHandler());
                    }
                });

        b.bind(8081).sync();
    }
}
