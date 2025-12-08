package com.example.booking.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
        private String token;

        public String getStatus() {
                return token;
        }

        public void setStatus(String status) {
                this.token = status;
        }
}
