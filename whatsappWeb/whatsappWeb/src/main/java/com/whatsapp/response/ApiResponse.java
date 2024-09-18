package com.whatsapp.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiResponse {

    private String message;
    private boolean status;
}
