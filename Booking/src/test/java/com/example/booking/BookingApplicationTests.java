package com.example.booking;

import com.example.booking.service.BookingGrpcService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.dao.TransientDataAccessException;

@SpringBootTest
class BookingApplicationTests {

    @Autowired
    private BookingGrpcService bookingService;

    @Test
    void contextLoads() throws InterruptedException {

        Runnable task1 = () -> {
            try {
                try {
                    System.out.println("-------Thread-1 start-------");
                    int result = bookingService.getResult1(7L, 1L, "Thread-1");
                    System.out.println("-------Thread-1 result-------" + result);
                } catch (CannotAcquireLockException  ex) {
                    System.out.println("Thread-1 retry due to serialization conflict");
                }

//                System.out.println("-------Thread-1 start-------");
//                int result = bookingService.getResult1(3L, "Thread-1");
//                System.out.println("-------Thread-1 result = " + result);
            } catch (Exception e) { e.printStackTrace(); }
        };

        Runnable task2 = () -> {
            try {
                try {
                    System.out.println("-------Thread-2 start-------");
                    int result = bookingService.getResult1(7L, 1L, "Thread-2");
                    System.out.println("-------Thread-2 result-------" + result);
                } catch (CannotAcquireLockException  ex) {
                    System.out.println("Thread-2 retry due to serialization conflict");
                }
//                System.out.println("-------Thread-2 start-------");
//                int result = bookingService.getResult1(3L, "Thread-2");
//                System.out.println("-------Thread-2 result = " + result);
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
