package com.example.authentication.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

        @Setter
        @Getter
        private String orderId;

        private List<Long> seatIds;

        private String status;
}
