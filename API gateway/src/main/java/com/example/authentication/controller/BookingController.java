package com.example.authentication.controller;

import com.example.authentication.entity.User;
import com.example.authentication.request.BookingRequest;
import com.example.authentication.request.OrderRequest;
import com.example.authentication.request.PaymentRequest;
import com.example.authentication.request.RegisterRequest;
import com.example.authentication.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/booking")
    public Map<String, Object> booking(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.booking(request)).getBody();
    }

    @PostMapping("/order")
    public Map<String, Object> order(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.order(request)).getBody();
    }

    @PostMapping("/payment")
    public Map<String, Object> order(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.payment(request)).getBody();
    }

    @GetMapping("/getAllEvents")
    public Map<String, Object> getEvents() {
        return ResponseEntity.ok(eventService.getAllEvents()).getBody();
    }

    @GetMapping("/getZonesByEvent/{eventId}")
    public Map<String, Object> getZonesByEvent(@PathVariable Long eventId) {
        return eventService.getZonesByEvent(eventId);
    }

    @GetMapping("/getSeatsByZone/{zoneId}")
    public Map<String, Object> getSeatsByZone(@PathVariable Long zoneId) {
        return eventService.getSeatsByZone(zoneId);
    }

}
