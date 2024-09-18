package com.whatsapp.modal;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Array;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String full_name;
    private String email;
    private String profile_picture;
    private String password;

}
