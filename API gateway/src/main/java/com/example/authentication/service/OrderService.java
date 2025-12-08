package com.example.authentication.service;


import com.example.authentication.request.OrderRequest;
import com.example.grpc.booking.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 👇 gRPC client tự động inject theo tên trong application.yml
    @GrpcClient("booking-service")
    private BookingServiceGrpc.BookingServiceBlockingStub blockingStub;

    public Map<String, Object> order(OrderRequest request) {
//         Tạo request từ proto
        GrpcOrderRequest grpcOrderRequest = GrpcOrderRequest.newBuilder()
                .setUserId(request.getUserId())
                .addAllSeatIds(request.getSeatIds())   // ❗ dùng addAll thay vì setSeatIds
                .setPrice(request.getPrice())
                .build();

        // Gọi tới AuthService (ở server)
        GrpcOrderResponse response = blockingStub.order(grpcOrderRequest);

        // Map kết quả trả về
        Map<String, Object> result = new HashMap<>();
        result.put("status", response.getStatus());
        result.put("orderId", response.getOrderId());
        return result;
    }
}
