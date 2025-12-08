package com.example.authentication.service;

import com.example.grpc.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Map;


@GrpcService
public class AuthGrpcService extends AuthServiceGrpc.AuthServiceImplBase {

    @Autowired
    private AuthService authService;

    @Override
    public void register(AuthRegisterRequest request,
                         StreamObserver<AuthRegisterResponse> responseObserver) {
        try {
            var user = new com.example.authentication.entity.User();
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());

            //TODO: validate duplicate username

            // Gọi service để lưu và sinh token
            var token = authService.register(user);

            // ✅ Trả response thành công
            var response = AuthRegisterResponse.newBuilder()
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

    @Override
    public void login(AuthLoginRequest request, StreamObserver<com.example.grpc.AuthLoginResponse> responseObserver) {
        var user = new com.example.authentication.entity.User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        Map<String, Object> result = authService.login(user);

        AuthLoginResponse response = AuthLoginResponse.newBuilder()
                .setStatus("SUCCESS")
                .setMessage(result.get("token").toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
