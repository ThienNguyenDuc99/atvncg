package com.example.authentication.service;


import com.example.authentication.request.PaymentRequest;
import com.example.grpc.booking.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 👇 gRPC client tự động inject theo tên trong application.yml
    @GrpcClient("booking-service")
    private BookingServiceGrpc.BookingServiceBlockingStub blockingStub;

    public Map<String, Object> getAllEvents() {

        Map<String, Object> responseMap = new HashMap<>();

        try {
            // Gửi request rỗng
            com.google.protobuf.Empty request = com.google.protobuf.Empty.newBuilder().build();

            // Gọi gRPC
            GrpcEventsResponse grpcResponse = blockingStub.getAllEvents(request);

            // Convert list Event trong gRPC thành List<Map>
            List<Map<String, Object>> eventList = grpcResponse.getEventsList().stream()
                    .map(event -> {
                        Map<String, Object> item = new HashMap<>();
                        item.put("eventId", event.getEventId());
                        item.put("eventName", event.getEventName());
                        item.put("eventDate", event.getEventDate());
                        item.put("eventLocation", event.getEventLocation());
                        return item;
                    })
                    .toList();

            // Trả về response
            responseMap.put("success", true);
            responseMap.put("events", eventList);

        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("message", e.getMessage());
        }

        return responseMap;
    }

    public Map<String, Object> getZonesByEvent(Long eventId) {

        Map<String, Object> responseMap = new HashMap<>();

        try {
            // Build request gRPC
            GrpcZonesRequest request = GrpcZonesRequest.newBuilder()
                    .setEventId(eventId)
                    .build();

            // Call gRPC
            GrpcZonesResponse grpcResponse = blockingStub.getZonesByEvent(request);

            // Convert list Zone → List<Map>
            List<Map<String, Object>> zoneList = grpcResponse.getZonesList().stream()
                    .map(zone -> {
                        Map<String, Object> item = new HashMap<>();
                        item.put("zoneId", zone.getZoneId());
                        item.put("price", zone.getPrice());
                        item.put("name", zone.getName());
                        return item;
                    })
                    .toList();

            responseMap.put("success", true);
            responseMap.put("zones", zoneList);

        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("message", e.getMessage());
        }

        return responseMap;
    }

    public Map<String, Object> getSeatsByZone(Long zoneId) {

        Map<String, Object> responseMap = new HashMap<>();

        try {
            // Build request for gRPC
            GrpcSeatsRequest request = GrpcSeatsRequest.newBuilder()
                    .setZoneId(zoneId)
                    .build();

            // Call gRPC
            GrpcSeatsResponse grpcResponse = blockingStub.getSeatsByZone(request);

            // Convert repeated Seat -> List<Map>
            List<Map<String, Object>> seatList = grpcResponse.getSeatsList().stream()
                    .map(seat -> {
                        Map<String, Object> item = new HashMap<>();
                        item.put("seatId", seat.getSeatId());
                        item.put("status", seat.getStatus());
                        return item;
                    })
                    .toList();

            responseMap.put("success", true);
            responseMap.put("seats", seatList);

        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("message", e.getMessage());
        }

        return responseMap;
    }
}
