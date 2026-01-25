package com.example.authentication.controller;

import com.example.authentication.request.BookingRequest;
import com.example.authentication.request.OrderRequest;
import com.example.authentication.request.PaymentRequest;
import com.example.authentication.security.SecurityUser;
import com.example.authentication.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/business")
@RequiredArgsConstructor
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EventService eventService;

    @Autowired
    private RequestTakerService requestTakerService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/booking")
    public Map<String, Object> booking(@RequestBody BookingRequest request) {
        SecurityUser user = (SecurityUser)
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();

        Long userId = user.getUserId();
        request.setUserId(userId);// ✅ LẤY ĐƯỢC userId
        return ResponseEntity.ok(bookingService.booking(request)).getBody();
    }

    @PostMapping("/order")
    public Map<String, Object> order(@RequestBody OrderRequest request) {
        SecurityUser user = (SecurityUser)
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();

        Long userId = user.getUserId();
        request.setUserId(userId);// ✅ LẤY ĐƯỢC userId
        return ResponseEntity.ok(orderService.order(request)).getBody();
    }

    @PostMapping("/payment")
    public Map<String, Object> payment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.payment(request)).getBody();
    }

    @GetMapping("/getAllEvents")
    public Map<String, Object> getEvents() {
        return ResponseEntity.ok(eventService.getAllEvents()).getBody();
    }

//    @GetMapping("/getAllEvents1")
//    public Map<String, Object> getEvents1() {
//        SecurityUser user = (SecurityUser)
//                SecurityContextHolder.getContext()
//                        .getAuthentication()
//                        .getPrincipal();
//
//        Long userId = user.getUserId();
//        String username = user.getUsername();
//        if (1 == 1) {
//            return ResponseEntity.ok(requestTakerService
//                    .sendQueue(userId, username)).getBody();
//        } else {
//            return ResponseEntity.ok(eventService.getAllEvents()).getBody();
//        }
//    }

    @GetMapping("/getZonesByEvent/{eventId}")
    public Map<String, Object> getZonesByEvent(@PathVariable Long eventId) {
        SecurityUser user = (SecurityUser)
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();

        Long userId = user.getUserId();
        String username = user.getUsername();
        if (1 == 1) {
            return ResponseEntity.ok(requestTakerService
                    .sendQueue(userId, username, eventId)).getBody();
        } else {
            return eventService.getZonesByEvent(eventId);
        }
    }

    @GetMapping("/getSeatsByZone/{zoneId}")
    public Map<String, Object> getSeatsByZone(@PathVariable Long zoneId) {
        return eventService.getSeatsByZone(zoneId);
    }

    @GetMapping(value = "/sse/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@RequestParam String token) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        try {
            emitter.send(SseEmitter.event()
                    .name("connected")
                    .data("SSE connected"));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }
}
