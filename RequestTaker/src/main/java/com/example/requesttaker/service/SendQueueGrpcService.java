package com.example.requesttaker.service;

import com.example.grpc.GrpcSendQueueRequest;
import com.example.grpc.GrpcSendQueueResponse;
import com.example.grpc.RequestTakerServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.catalina.User;
import org.springframework.dao.DataIntegrityViolationException;

@GrpcService
public class SendQueueGrpcService extends RequestTakerServiceGrpc.RequestTakerServiceImplBase {
    @Override
    public void sendQueue(GrpcSendQueueRequest request,
                          StreamObserver<GrpcSendQueueResponse> responseObserver) {
        try {
            String username = request.getUsername();
            Long userId = request.getUserId();

            // ✅ Trả response thành công
            var response = GrpcSendQueueResponse.newBuilder()
                    .setUsername(username)
                    .setUserId(userId)
                    .setStatus("SUCCESS")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (DataIntegrityViolationException e) {
            // Lỗi ràng buộc DB như trùng username
            responseObserver.onError(
                    Status.ALREADY_EXISTS.withDescription("Username already exists").asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription("Registration failed: " + e.getMessage()).asRuntimeException()
            );
        }
    }
}
