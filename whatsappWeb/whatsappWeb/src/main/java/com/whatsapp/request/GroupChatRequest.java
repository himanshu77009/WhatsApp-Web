package com.whatsapp.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupChatRequest {

    public List<Integer> userIds;
    private String chat_name;
    private String chat_image;
}
