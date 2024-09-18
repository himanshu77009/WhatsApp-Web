package com.whatsapp.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponse {

    private String jwt;
    private boolean isAuth;
}
