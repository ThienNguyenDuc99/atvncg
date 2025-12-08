package com.example.authentication.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

        private Long orderId;

        private Long status;

        private Long totalPrice;

}
