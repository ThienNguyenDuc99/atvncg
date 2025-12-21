package com.example.booking.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookingTest {

    @Autowired
    private BookingGrpcService bookingService;

    @Test
    void testConcurrency() throws Exception {

        Runnable task1 = () -> {
            try {
                int result = bookingService.getResult(1L, 1L,"Thread-1");
                System.out.println("Thread-1 result = " + result);
            } catch (Exception e) { e.printStackTrace(); }
        };

        Runnable task2 = () -> {
            try {
                int result = bookingService.getResult(1L, 1L, "Thread-2");
                System.out.println("Thread-2 result = " + result);
            } catch (Exception e) { e.printStackTrace(); }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}