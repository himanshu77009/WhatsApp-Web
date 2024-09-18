package com.whatsapp.controller;

import com.whatsapp.exception.UserException;
import com.whatsapp.modal.User;
import com.whatsapp.request.UpdateUserRequest;
import com.whatsapp.response.ApiResponse;
import com.whatsapp.service.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserServices userServices;

    public UserController(UserServices userServices){
        this.userServices = userServices;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {
        User user = userServices.findUserProfile(token);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{query")
    public ResponseEntity<List<User>> searchUserHandler(@PathVariable("query") String q){
        List<User> users = userServices.searchUser(q);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUserHandler(@RequestBody UpdateUserRequest req, @RequestHeader("Authorization") String token) throws UserException {
        User user = userServices.findUserProfile(token);
        userServices.updateUser(user.getId(), req);

        ApiResponse res = new ApiResponse("User updated successfully", true);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
}
