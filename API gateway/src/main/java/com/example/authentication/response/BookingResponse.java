package com.example.authentication.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {

        @Setter
        @Getter
        private String bookingId;

        private List<Long> seatIds;

        private String status;
}
