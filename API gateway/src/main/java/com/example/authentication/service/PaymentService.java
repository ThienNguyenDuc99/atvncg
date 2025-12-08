package com.example.authentication.service;


import com.example.authentication.request.OrderRequest;
import com.example.authentication.request.PaymentRequest;
import com.example.grpc.booking.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 👇 gRPC client tự động inject theo tên trong application.yml
    @GrpcClient("booking-service")
    private BookingServiceGrpc.BookingServiceBlockingStub blockingStub;

    public Map<String, Object> payment(PaymentRequest request) {
//         Tạo request từ proto
        GrpcPaymentRequest grpcPaymentRequest = GrpcPaymentRequest.newBuilder()
                .setOrderId(request.getOrderId())
                .setStatus(request.getStatus())
                .setTotalPrice(request.getTotalPrice())
                .build();

        // Gọi tới AuthService (ở server)
        GrpcPaymentResponse response = blockingStub.payment(grpcPaymentRequest);

        // Map kết quả trả về
        Map<String, Object> result = new HashMap<>();
        result.put("status", response.getStatus());
        result.put("orderId", response.getOrderId());
        return result;
    }
}
