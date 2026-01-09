package com.example.requesttaker.service;

import com.example.grpc.GrpcSendQueueRequest;
import com.example.grpc.GrpcSendQueueResponse;
import com.example.grpc.RequestTakerServiceGrpc;
import com.example.requesttaker.dto.RedisQueueObj;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

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

            RedisQueueObj redisQueueObj =
                    new RedisQueueObj(userId, username);

            // ✅ ADD TO REDIS ZSET
            redisQueueService.addToQueue(redisQueueObj);

            var response = GrpcSendQueueResponse.newBuilder()
                    .setUsername(username)
                    .setUserId(userId)
                    .setStatus("SUCCESS")
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

