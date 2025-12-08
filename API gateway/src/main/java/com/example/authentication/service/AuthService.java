package com.example.authentication.service;


import com.example.authentication.entity.User;
import com.example.grpc.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 👇 gRPC client tự động inject theo tên trong application.yml
    @GrpcClient("booking-service")
    private AuthServiceGrpc.AuthServiceBlockingStub authStub;

    public Map<String, Object> register(com.example.authentication.request.RegisterRequest user) {
        // Mã hoá mật khẩu trước khi gửi qua gRPC
//        String hashed = encoder.encode(user.getPassword());

        // Tạo request từ proto
        RegisterRequest request = RegisterRequest.newBuilder()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .build();

        // Gọi tới AuthService (ở server)
        RegisterResponse response = authStub.register(request);

        // Map kết quả trả về
        Map<String, Object> result = new HashMap<>();
        result.put("status", response.getStatus());
        return result;
    }

    public Map<String, Object> login(User user) {
        // Tạo request từ proto
        LoginRequest request = LoginRequest.newBuilder()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .build();

        // Gọi tới AuthService (ở server)
        LoginResponse response = authStub.login(request);
        // Map kết quả trả về
        Map<String, Object> result = new HashMap<>();
        result.put("status", response.getStatus());
        result.put("token", response.getMessage());
        return result;
    }
}
