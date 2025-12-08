package com.example.authentication.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
@NoArgsConstructor
public class PaymentRequest {

        private Long orderId;

        private String status;

        private Long totalPrice;

        private List<Long> seatIds;

}
