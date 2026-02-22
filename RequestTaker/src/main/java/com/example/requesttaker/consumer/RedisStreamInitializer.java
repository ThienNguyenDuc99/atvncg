//package com.example.requesttaker.consumer;
//
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.connection.stream.ReadOffset;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class RedisStreamInitializer {
//
//    private static final Logger LOGGER = LogManager.getLogger(RedisStreamInitializer.class);
//
//    private final RedisTemplate<String, Object> redisTemplate;
//
//    @Value("${spring.data.redis.stream.key}")
//    private String streamKey;
//
//    @Value("${spring.data.redis.stream.group}")
//    private String group;
//
//    public RedisStreamInitializer(RedisTemplate<String, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//
//    @PostConstruct
//    public void init() {
//        try {
//            redisTemplate.opsForStream().createGroup(
//                    streamKey,
//                    ReadOffset.latest(),
//                    group
//            );
//            LOGGER.info("Created Redis stream group {}", group);
//        } catch (Exception e) {
//            LOGGER.info("Redis stream group already exists");
//        }
//    }
//}
