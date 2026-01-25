package com.example.booking.service;

import com.example.booking.entity.*;
import com.example.booking.repository.*;
import com.example.booking.security.SecurityUser;
import com.example.grpc.booking.*;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@GrpcService
public class BookingGrpcService extends BookingServiceGrpc.BookingServiceImplBase {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ZoneRepository zoneRepository;


    private static final Logger LOGGER = LogManager.getLogger(BookingGrpcService.class);


    @Override
    public void createBooking(GrpcBookingRequest request,
                              StreamObserver<GrpcBookingResponse> responseObserver) {

        LOGGER.info("SERVER RECEIVED CREATE_BOOKING"); // log check

        List<Long> seatIdsList = request.getSeatIdsList();
        Long userId = request.getUserId();
        int result = 0;
        try {
            for (Long seatId : seatIdsList) {
                result = getResult(seatId, userId, "Main-Thread");
            }
        } catch (Exception e) {
            // Log error
            LOGGER.error("ERROR during booking seatId=" + seatIdsList + " -> " + e.getMessage());
            e.printStackTrace();
            result = 0; // failed
        }

        if (result == 1) {
            LOGGER.info("UserId: " + userId + ", Booking SUCCESS for seats: " + seatIdsList);
        } else {
            LOGGER.info("UserId: " + userId + ", Booking FAILED for seats: " + seatIdsList);
        }

        responseObserver.onNext(result == 1 ?
                GrpcBookingResponse.newBuilder()
                        .setStatus("SUCCESS")
                        .build()
                :
                GrpcBookingResponse.newBuilder()
                        .setStatus("FAILED")
                        .build()
        );
        responseObserver.onCompleted();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int getResult(Long seatId, Long userId, String thread) {
        try {
            Seat seat = seatRepository.findById(seatId).orElse(null);
            String status = seat.getStatus();
            LocalDateTime expireTime = seat.getExpireTime();
//            try {
//                Thread.sleep(10000); // Simulate delay for testing concurrency
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            int result = 0;
            LocalDateTime now = LocalDateTime.now();
            if (status.equals("Available") || (status.equals("BOOKED") &&
                    expireTime.isBefore(now))) {
                result = seatRepository.bookSeat(seatId);
                LOGGER.info(thread + " BOOKED SEAT " + seatId
                        + " RESULT = " + result + " AT " + now + " WITH " + userId); // log check
            }
            return result;
        } catch (CannotAcquireLockException ex) {
            // Concurrency conflict: Postgres 40001
            LOGGER.error(thread + " FAILED due to concurrency lock (40001)");
            return 0;
        }
            catch (DataIntegrityViolationException ex) {
            LOGGER.error(thread + " FAILED due to constraint violation");
            return 0;
        }
            catch (Exception ex) {
            LOGGER.error(thread + " FAILED due to unexpected error: " + ex.getMessage());
            return 0;
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int getResult1(Long seatId, String thread) {
        try {
            int result = seatRepository.bookSeat1(seatId);
            LOGGER.info(thread + " booked seat " + seatId
                    + " result = " + result + " at " + LocalDateTime.now()); // log check
            return result;
        } catch (CannotAcquireLockException ex) {
            // Concurrency conflict: Postgres 40001
            LOGGER.error(thread + " FAILED due to concurrency lock (40001)");
            return 0;
        }
        catch (DataIntegrityViolationException ex) {
            LOGGER.error(thread + " FAILED due to constraint violation");
            return 0;
        }
        catch (Exception ex) {
            LOGGER.error(thread + " FAILED due to unexpected error: " + ex.getMessage());
            return 0;
        }
    }

    @Override
    public void order(GrpcOrderRequest request,
                      StreamObserver<GrpcOrderResponse> responseObserver) {

        System.out.println("SERVER RECEIVED CREATE_ORDER"); // log check

        Long seatId = request.getSeatIdsList().get(0);
        Long userId = request.getUserId();
        Long price = request.getPrice();


        Long eventId = seatRepository.findEventIdBySeatIdLimit1(seatId);

        int result = 0;
        Order order = new Order();
        order.setEventId(eventId);
        order.setUserId(userId);
        order.setTotalPrice(price);
        order.setStatus("CREATED");
        order.setCreatedAt(LocalDateTime.now());

        Order saved = orderRepository.save(order);

        if (saved.getId() != null) {
            LOGGER.info("UserId: " + userId + " lưu thành công, ID = "
                    + saved.getId() + " cho cac ghe: " + request.getSeatIdsList());
            result = 1;
        } else {
            LOGGER.error("Lưu thất bại");
        }

        responseObserver.onNext(result == 1 ?
                GrpcOrderResponse.newBuilder()
                        .setOrderId(saved.getId())
                        .setStatus("SUCCESS")
                        .build()
                :
                GrpcOrderResponse.newBuilder()
                        .setStatus("FAILED")
                        .build()
        );
        responseObserver.onCompleted();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void payment(GrpcPaymentRequest request,
                      StreamObserver<GrpcPaymentResponse> responseObserver) {
    Long userId = 0L;
    try {
        //TODO:: get userId by orderId

        Long orderId = request.getOrderId();
        String status = request.getStatus();
        Long totalPrice = request.getTotalPrice();
        List<Long> listSeatIds = request.getSeatIdsList();


        Optional<Order> order = orderRepository.findById(orderId);
        
        String orderStatus;
        if (order.isEmpty()) {
            LOGGER.info("Order ID = " + orderId + " không tồn tại.");
            responseObserver.onNext(
                    GrpcPaymentResponse.newBuilder()
                            .setStatus("ORDER_NOT_FOUND")
                            .build()
            );
            responseObserver.onCompleted();
            return;
        } else {
            orderStatus = order.get().getStatus();
            userId = order.get().getUserId();
            System.out.println(
                    "SERVER RECEIVED CREATE_PAYMENT AT "
                            + LocalDateTime.now()
                            + " FOR " + listSeatIds
                            + " AND " + orderId
                            + " AND " + userId
            );
        }
        if (Objects.isNull(orderStatus) || orderStatus.equals("CREATED")) {
            orderRepository.updateStatusById(orderId, "PAID");

            Payment payment = new Payment();
            payment.setOrderId(orderId);
            payment.setStatus("PAID");
            payment.setTotalPrice(totalPrice);
            payment.setCreatedAt(LocalDateTime.now());

            paymentRepository.save(payment);

            seatRepository.updateStatusByIds(listSeatIds, "BOOKED", orderId, userId);

//            LOGGER.info("Lưu thông tin payment cho Order ID = " + orderId);
            LOGGER.info("UserId :" + userId + ", Order ID = " + orderId + " với  đã được cập nhật trạng thái thành PAID " +
                    "cho các ghe: " + listSeatIds);
        }
        if (orderStatus.equals("PAID")) {
            LOGGER.info("Order ID = " + orderId + " đã được thanh toán trước đó.");
            responseObserver.onNext(
                    GrpcPaymentResponse.newBuilder()
                            .setStatus("ALREADY_PAID")
                            .build()
            );
            responseObserver.onCompleted();
            return;
        }


        responseObserver.onNext(
                GrpcPaymentResponse.newBuilder()
                        .setStatus("SUCCESS")
                        .build()
        );
        responseObserver.onCompleted();
    } catch (CannotAcquireLockException e) {

        // ⚠️ concurrency / lock / serialization failure
        LOGGER.error("Concurrency error when payment", e);
        long orderId = request.getOrderId();
        List<Long> listSeatIds = request.getSeatIdsList();
        LOGGER.error(
                "ERROR WHEN PAYMENT AT "
                        + LocalDateTime.now()
                        + " FOR listSeatIds:" + listSeatIds
                        + " AND orderId:" + orderId
                        + " AND userId:" + userId
        );

        responseObserver.onNext(
                GrpcPaymentResponse.newBuilder()
                        .setStatus("FAIL")
                        .build()
        );
        responseObserver.onCompleted();

        // 👉 bắt buộc rollback
        throw e;

    } catch (Exception e) {

        LOGGER.error("Unexpected error in payment()", e);

        responseObserver.onNext(
                GrpcPaymentResponse.newBuilder()
                        .setStatus("FAILED")
                        .build()
        );
        responseObserver.onCompleted();

        // 👉 rollback toàn bộ transaction
        throw e;
        }
    }

    @Override
    public void getAllEvents(Empty request,
                             StreamObserver<GrpcEventsResponse> responseObserver) {

        // Lấy danh sách events từ DB
        List<Event> events = eventRepository.findAll();

        // Build response cho gRPC
        GrpcEventsResponse.Builder response = GrpcEventsResponse.newBuilder();

        for (Event e : events) {
            response.addEvents(
                    GrpcEvent.newBuilder()
                            .setEventId(e.getId())
                            .setEventName(e.getName())
                            .setEventDate(
                                    e.getStartTime() != null ? e.getStartTime().toString() : ""
                            )
                            .setEventLocation(e.getLocation())
                            .build()
            );
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getZonesByEvent(GrpcZonesRequest request,
                         StreamObserver<GrpcZonesResponse> responseObserver) {

        long eventId = request.getEventId();

        // Lấy list zones theo eventId từ DB/service
        List<Zone> zones = zoneRepository.findByEventId(eventId);

        GrpcZonesResponse.Builder response = GrpcZonesResponse.newBuilder();

        for (Zone z : zones) {
            response.addZones(
                    GrpcZone.newBuilder()
                            .setZoneId(z.getId())
                            .setName(z.getName())
                            .setPrice(z.getPrice())
                            .build()
            );
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getSeatsByZone(GrpcSeatsRequest request,
                         StreamObserver<GrpcSeatsResponse> responseObserver) {

        long zoneId = request.getZoneId();

        // Lấy list seats theo zoneId
        List<Seat> seats = seatRepository.findByZoneId(zoneId);

        GrpcSeatsResponse.Builder response = GrpcSeatsResponse.newBuilder();

        for (Seat s : seats) {
            response.addSeats(
                    GrpcSeat.newBuilder()
                            .setSeatId(s.getId())
                            .setStatus(s.getStatus())   // "AVAILABLE", "BOOKED", ...
                            .build()
            );
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
