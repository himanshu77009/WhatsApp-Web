package com.whatsapp.request;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateUserRequest {

    private String full_name;
    private String profile_picture;
}
