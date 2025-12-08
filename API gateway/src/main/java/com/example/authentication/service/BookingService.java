package com.example.authentication.service;


import com.example.authentication.request.BookingRequest;
import com.example.grpc.booking.BookingServiceGrpc;
import com.example.grpc.booking.GrpcBookingRequest;
import com.example.grpc.booking.GrpcBookingResponse;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 👇 gRPC client tự động inject theo tên trong application.yml
    @GrpcClient("booking-service")
    private BookingServiceGrpc.BookingServiceBlockingStub blockingStub;

    public Map<String, Object> booking(BookingRequest request) {
//         Tạo request từ proto
        GrpcBookingRequest grpcBookingRequest = GrpcBookingRequest.newBuilder()
                .setUserId(request.getUserId())
                .addAllSeatIds(request.getSeatIds())
                .build();

        // Gọi tới AuthService (ở server)
        GrpcBookingResponse response = blockingStub.createBooking(grpcBookingRequest);

        // Map kết quả trả về
        Map<String, Object> result = new HashMap<>();
        result.put("status", response.getStatus());
        return result;
    }
}
