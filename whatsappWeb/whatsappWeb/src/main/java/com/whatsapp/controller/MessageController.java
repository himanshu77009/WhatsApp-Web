package com.whatsapp.controller;

import com.whatsapp.exception.ChatException;
import com.whatsapp.exception.MessageException;
import com.whatsapp.exception.UserException;
import com.whatsapp.modal.Message;
import com.whatsapp.modal.User;
import com.whatsapp.request.SendMessageRequest;
import com.whatsapp.response.ApiResponse;
import com.whatsapp.service.MessageService;
import com.whatsapp.service.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private MessageService messageService;
    private UserServices userServices;

    public MessageController(MessageService messageService, UserServices userServices){
        this.messageService = messageService;
        this.userServices = userServices;
    }

    @PostMapping("/create")
    public ResponseEntity<Message> sendMessageHandler(@RequestBody SendMessageRequest req, @RequestHeader("Authorization") String jwt) throws ChatException, UserException {
        User user = userServices.findUserProfile(jwt);

        req.setUserId((user.getId()));
        Message message = messageService.sendMessage(req);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getChatsMessagesHandler(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) throws ChatException, UserException {
        User user = userServices.findUserProfile(jwt);

        List<Message> messages = messageService.getChatsMessages(chatId, user);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @DeleteMapping("/chat/{messageId}")
    public ResponseEntity<ApiResponse> deleteMessageHandler(@PathVariable Integer messageId, @RequestHeader("Authorization") String jwt) throws UserException, MessageException {
        User user = userServices.findUserProfile(jwt);

        messageService.deleteMessage(messageId, user);

        ApiResponse res = new ApiResponse("message deleted", false);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
