package com.example.booking.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SeatAvailabilityPublisher {

    private static final String CHANNEL = "seat-available-topic";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Scheduled(fixedRate = 30000)
    public void publishAvailableSeats() {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM atvncg_business.seats WHERE status = 'Available'",
                    Integer.class
            );

            redisTemplate.convertAndSend(CHANNEL, String.valueOf(count));

            System.out.println("Published available seats: " + count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}