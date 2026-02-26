package com.example.authentication.service;

import com.example.authentication.dto.NotificationQueueDto;
import com.example.grpc.GrpcSendQueueRequest;
import com.example.grpc.GrpcSendQueueResponse;
import com.example.grpc.RequestTakerServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RequestTakerService {

    private static Map<NotificationQueueDto, String> notificationQueueDto2TraceId = new HashMap<>();

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private static final Logger LOGGER = LogManager.getLogger(RequestTakerService.class);

    // 👇 gRPC client tự động inject theo tên trong application.yml
    @GrpcClient("request-taker-service")
    private RequestTakerServiceGrpc.RequestTakerServiceBlockingStub blockingStub;

    public Map<String, Object> sendQueue(Long userId, String username, Long eventId, String traceId) {
        // Tạo request từ proto
        GrpcSendQueueRequest grpcBookingRequest = GrpcSendQueueRequest.newBuilder()
                .setUserId(userId)
                .setUsername(username)
                .setEventId(eventId)
                .setTraceId(traceId)
                .build();

        Map<String, Object> result = new HashMap<>();
        NotificationQueueDto notificationQueueDto = new NotificationQueueDto(userId, eventId);
        if (notificationQueueDto2TraceId.containsKey(notificationQueueDto)) {
            LOGGER.error("send queue status {} for eventId {} and userId {}",
                    "FAIL", eventId, userId);
            result.put("status", "FAILURE");
            result.put("message", "traceId already exist!");
            result.put("traceId", traceId);
            // TODO: create new thread to save to database
            return result;
        } else {
            notificationQueueDto2TraceId.put(notificationQueueDto, traceId);

            // Gọi tới AuthService (ở server)
            GrpcSendQueueResponse response = blockingStub.sendQueue(grpcBookingRequest);

            // Map kết quả trả về
            result.put("status", response.getStatus());
            result.put("userId", response.getUserId());
            result.put("eventId", response.getEventId());
            result.put("rank", response.getRank());
            result.put("traceId", traceId);
            LOGGER.info("send queue status {} for eventId {} and userId {}",
                    response.getStatus(), response.getEventId(), response.getUserId());
            return result;
        }
    }
}
