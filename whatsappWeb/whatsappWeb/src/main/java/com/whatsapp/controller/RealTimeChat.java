package com.whatsapp.controller;

public class RealTimeChat {

    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/group/public")
    public Message receiveMessage(@Payload Message message){
        
        simpMessagingTemplate.convertAndSend("/group/"+ message.getChat().getId().toString(), message);

        return message;
    }
}
