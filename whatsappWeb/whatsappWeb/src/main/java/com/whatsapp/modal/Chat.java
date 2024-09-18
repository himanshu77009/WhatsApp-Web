package com.whatsapp.modal;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String chat_name;
    private String chat_image;

    @ManyToMany
    private Set<User> admins = new HashSet<>();

    @Column(name = "is_group")
    private boolean isGroup;

    @JoinColumn(name = "created_by")
    @ManyToOne
    private User createdBy;

    @ManyToMany
    private Set<User> users = new HashSet<>();

    @OneToMany
    private List<Message> messages = new ArrayList<>();
}
