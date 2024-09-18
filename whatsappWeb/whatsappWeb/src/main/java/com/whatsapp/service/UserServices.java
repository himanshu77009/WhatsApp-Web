package com.whatsapp.service;

import com.whatsapp.exception.UserException;
import com.whatsapp.modal.User;
import com.whatsapp.request.UpdateUserRequest;

import java.util.List;

public interface UserServices {

    public User findUserById(Integer id) throws UserException;

    public User findUserProfile(String jwt) throws UserException;

    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException;

    public List<User> searchUser(String query);
}
