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
public class OrderRequest {

        private Long userId;

        private List<Long> seatIds;

        private Long price;

}
