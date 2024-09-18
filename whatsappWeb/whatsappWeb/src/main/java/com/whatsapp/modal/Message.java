package com.whatsapp.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;

    private LocalDateTime timeStamp;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
}
