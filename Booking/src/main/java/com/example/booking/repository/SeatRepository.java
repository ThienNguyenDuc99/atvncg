package com.example.booking.repository;

import com.example.booking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Transactional
    @Modifying
    @Query(
            value = "UPDATE atvncg_business.seats " +
                    "SET status = 'LOCKED', expire_time = NOW() + INTERVAL '1 minute' " +
                    "WHERE id = :seatId",
            nativeQuery = true
    )
    int bookSeat(Long seatId);


    @Transactional
    @Modifying
    @Query(
            value = "UPDATE atvncg_business.seats\n" +
                    "    SET status='BOOKED', expire_time = now() + interval '1 minute'\n" +
                    "    WHERE id=:seatId\n" +
                    "    AND (\n" +
                    "                    status='AVAILABLE'\n" +
                    "                    OR (status='BOOKED' AND expire_time < now())\n" +
                    "            );",
            nativeQuery = true
    )
    int bookSeat1(Long seatId);



    Optional<Seat> findById(Long seatId);

    @Query(value = "select e.id from atvncg_business.seats s inner join atvncg_business.\"zone\" z on s.zone_id  = z.id \n" +
            "inner join atvncg_business.events e on e.id = z.event_id WHERE s.id = :seatId LIMIT 1",
            nativeQuery = true)
    Long findEventIdBySeatIdLimit1(@Param("seatId") Long seatId);

    List<Seat> findByZoneId(Long zoneId);

    @Modifying
    @Query("UPDATE Seat s SET s.status = :status, s.orderId = :orderId WHERE s.id IN :ids")
    int updateStatusByIds(@Param("ids") List<Long> ids,
                          @Param("status") String status,
                          @Param("orderId") Long orderId);
}

//    @Transactional
//    @Modifying
//    @Query(
//            value = "UPDATE atvncg_business.seats " +
//                    "SET order_id = 'BOOKED', expire_time = NOW() + INTERVAL '1 minute' " +
//                    "WHERE id = :seatId",
//            nativeQuery = true
//    )
//    int bookSeat(Long seatId);
//}

