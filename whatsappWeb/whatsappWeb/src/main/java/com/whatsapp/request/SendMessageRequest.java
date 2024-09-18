package com.whatsapp.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SendMessageRequest {

    private Integer userId;
    private Integer chatId;
    private String content;
}
