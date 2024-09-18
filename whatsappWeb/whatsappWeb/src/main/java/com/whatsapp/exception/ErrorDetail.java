package com.whatsapp.exception;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorDetail {

    private String error;
    private String message;
    private LocalDateTime timeStamp;


}
