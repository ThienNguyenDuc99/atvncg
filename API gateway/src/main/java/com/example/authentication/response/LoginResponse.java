package com.example.authentication.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

        @Setter
        @Getter
        private String token;
}
