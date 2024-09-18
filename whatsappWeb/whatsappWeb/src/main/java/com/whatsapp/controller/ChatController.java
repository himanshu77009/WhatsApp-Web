package com.whatsapp.controller;

import com.whatsapp.exception.ChatException;
import com.whatsapp.exception.UserException;
import com.whatsapp.modal.Chat;
import com.whatsapp.modal.User;
import com.whatsapp.request.GroupChatRequest;
import com.whatsapp.request.SingleChatRequest;
import com.whatsapp.response.ApiResponse;
import com.whatsapp.service.ChatService;
import com.whatsapp.service.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private ChatService chatService;
    private UserServices userServices;

    public ChatController(ChatService chatService, UserServices userServices){
        this.chatService = chatService;
        this.userServices = userServices;
    }

    @PostMapping("/single")
    public ResponseEntity<Chat> createChatHandler(@RequestBody SingleChatRequest singleChatRequest, @RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userServices.findUserProfile(jwt);
        Chat chat = chatService.createChat(reqUser, singleChatRequest.getUserId());
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/group")
    public ResponseEntity<Chat> createGroupHandler(@RequestBody GroupChatRequest req, @RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userServices.findUserProfile(jwt);
        Chat chat = chatService.createGroup(req, reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        Chat chat = chatService.findChatById(chatId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Chat>> findAllChatByUserIdHandler(@RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userServices.findUserProfile(jwt);
        List<Chat> chats = chatService.findAllChatByUserId(reqUser.getId());
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @PutMapping("/{chatId}/add/{userId}")
    public ResponseEntity<Chat> addUserToGroupHandler(@PathVariable Integer chatId, @PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userServices.findUserProfile(jwt);
        Chat chat = chatService.addUserToGroup(userId, chatId, reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PutMapping("/{chatId}/remove/{userId}")
    public ResponseEntity<Chat> removeUserFromGroupHandler(@PathVariable Integer chatId, @PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userServices.findUserProfile(jwt);
        Chat chat = chatService.removeFromGroup(chatId,userId, reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{chatId}")
    public ResponseEntity<ApiResponse> deleteChatHandler(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userServices.findUserProfile(jwt);
        chatService.deleteChat(chatId, reqUser.getId());

        ApiResponse res = new ApiResponse("chat is deleted", true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }




}
