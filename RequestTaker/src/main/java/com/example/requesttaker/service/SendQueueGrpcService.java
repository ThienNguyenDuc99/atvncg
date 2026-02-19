package com.example.requesttaker.service;

import com.example.grpc.GrpcSendQueueRequest;
import com.example.grpc.GrpcSendQueueResponse;
import com.example.grpc.RequestTakerServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@GrpcService
public class SendQueueGrpcService
        extends RequestTakerServiceGrpc.RequestTakerServiceImplBase {

    @Autowired
    private RedisQueueService redisQueueService;

    @Override
    public void sendQueue(GrpcSendQueueRequest request,
                          StreamObserver<GrpcSendQueueResponse> responseObserver) {
        try {
            String username = request.getUsername();
            Long userId = request.getUserId();
            Long eventId = request.getEventId();
            String traceId = request.getTraceId();

//            // ✅ ADD TO REDIS ZSET
//            Map<String, Object> payload = new HashMap<>();
//            payload.put("userId", userId);
//            payload.put("username", username);
//            payload.put("eventId", eventId);
            String member = userId + ":" + eventId + ":" + traceId;

            redisQueueService.addToQueue(member);
            long rank = redisQueueService.getRank(member);

            var response = GrpcSendQueueResponse.newBuilder()
                    .setUsername(username)
                    .setUserId(userId)
                    .setEventId(eventId)
                    .setStatus("SUCCESS")
                    .setRank(rank)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription("Registration failed: " + e.getMessage())
                            .asRuntimeException()
            );
        }
    }
}

