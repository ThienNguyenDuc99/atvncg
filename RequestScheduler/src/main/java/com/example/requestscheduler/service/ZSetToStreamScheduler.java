package com.example.requestscheduler.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ZSetToStreamScheduler {

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${scheduler.zset-key}")
    private String zsetKey;

    @Value("${scheduler.stream-key}")
    private String streamKey;

    private static final int BATCH_SIZE = 200;

    private static final String LUA_SCRIPT = """
         local zset = KEYS[1]
         local stream = KEYS[2]
         local now = ARGV[1]
         local batch = tonumber(ARGV[2])
         
         local items = redis.call('ZRANGEBYSCORE', zset, '0', now, 'LIMIT', '0', batch)
         
         for _, member in ipairs(items) do
         
             local first = string.find(member, ":")
             local second = string.find(member, ":", first + 1)
         
             if first and second then
         
                 local userId = string.sub(member, 1, first - 1)
                 local eventId = string.sub(member, first + 1, second - 1)
                 local traceId = string.sub(member, second + 1)
         
                 redis.call('XADD', stream, '*',
                     'userId', userId,
                     'eventId', eventId,
                     'traceId', traceId,
                     'scheduledTime', now
                 )
         
             end
         
             redis.call('ZREM', zset, member)
         end
         
         return #items
        """;

    private final DefaultRedisScript<Long> script = createScript();

    private DefaultRedisScript<Long> createScript() {
        DefaultRedisScript<Long> s = new DefaultRedisScript<>();
        s.setScriptText(LUA_SCRIPT);
        s.setResultType(Long.class);
        return s;
    }

    /**
     * Scheduler poll atomic
     */
    @Scheduled(fixedDelay = 10000)
    public void pollAtomically() {

        long now = System.currentTimeMillis();

        Long moved = redisTemplate.execute(
                script,
                List.of(zsetKey, streamKey),
                String.valueOf(now),
                String.valueOf(BATCH_SIZE)
        );

        if (moved != null && moved > 0) {
            log.info("Moved {} scheduled events to stream", moved);
        }
    }
}
