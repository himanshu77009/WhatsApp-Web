package com.whatsapp.service;

import com.whatsapp.exception.ChatException;
import com.whatsapp.exception.UserException;
import com.whatsapp.modal.Chat;
import com.whatsapp.modal.User;
import com.whatsapp.repository.ChatRepository;
import com.whatsapp.request.GroupChatRequest;

import java.util.List;
import java.util.Optional;

public class ChatServiceImpl implements ChatService{

    private ChatRepository chatRepository;
    private UserServices userServices;

    public ChatServiceImpl(ChatRepository chatRepository,UserServices userServices){
        this.chatRepository = chatRepository;
        this.userServices = userServices;
    }

    @Override
    public Chat createChat(User reqUser, Integer userId2) throws UserException {
        User user = userServices.findUserById(userId2);

        Chat isChatExist = chatRepository.findSingleChatByUserIds(user, reqUser);

        if(isChatExist != null){
            return  isChatExist;
        }

        Chat chat = new Chat();
        chat.setCreatedBy(reqUser);
        chat.getUsers().add(user);
        chat.getUsers().add(reqUser);
        chat.setGroup(false);

        return chat;
    }

    @Override
    public Chat findChatById(Integer chatId) throws ChatException {
        Optional<Chat> chat = chatRepository.findById(chatId);

        if(chat.isPresent()){
            return chat.get();
        }
        throw new ChatException("Chat not found with chatId" + chatId);
    }

    @Override
    public List<Chat> findAllChatByUserId(Integer userId) throws UserException {
        User user = userServices.findUserById(userId);
        List<Chat> chats = chatRepository.findChatByUserId(user.getId());
        return chats;
    }

    @Override
    public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException {
        Chat group = new Chat();
        group.setGroup(true);
        group.setChat_image(req.getChat_image());
        group.setChat_name(req.getChat_name());
        group.setCreatedBy(reqUser);
        group.getAdmins().add(reqUser);

        for(Integer userId : req.getUserIds()){
            User user = userServices.findUserById(userId);
            group.getUsers().add(user);
        }

        return group;
    }

    @Override
    public Chat addUserToGroup(Integer userId, Integer chatId, User reqUser) throws UserException, ChatException {
        Optional<Chat> opt = chatRepository.findById(chatId);
        User user = userServices.findUserById(userId);

        if(opt.isPresent()){
            Chat chat = opt.get();
            if(chat.getAdmins().contains(reqUser)){
                chat.getUsers().add(user);
                return chatRepository.save(chat);
            }else{
                throw new UserException("You are not admin");
            }
        }
        throw new ChatException("Chat not found with id" + chatId);
    }

    @Override
    public Chat renameGroup(Integer chatId, String groupName, User reqUser) throws UserException, ChatException {
        Optional<Chat> opt = chatRepository.findById(chatId);

        if(opt.isPresent()){
            Chat chat = opt.get();
            if(chat.getUsers().contains(reqUser)){
                chat.setChat_name(groupName);
                return chatRepository.save(chat)
            }
            throw new UserException("You are not the member of this group");
        }
        throw new ChatException("Chat not found with id"+chatId);

    }

    @Override
    public Chat removeFromGroup(Integer chatId, Integer userId, User reqUser) throws UserException, ChatException {
        Optional<Chat> opt = chatRepository.findById(chatId);
        User user = userServices.findUserById(userId);

        if(opt.isPresent()){
            Chat chat = opt.get();
            if(chat.getAdmins().contains(reqUser)){
                chat.getUsers().remove(user);
                return chatRepository.save(chat);
            }else if(chat.getUsers().contains(reqUser)){
                if(user.getId().equals(reqUser.getId())){
                    chat.getUsers().remove(user);
                    return chatRepository.save(chat);
                }
            }

            throw new UserException("You can't remove another user");

        }
        throw new ChatException("Chat not found with id" + chatId);
    }

    @Override
    public void deleteChat(Integer chatId, Integer userId) throws UserException, ChatException {
        Optional<Chat> opt = chatRepository.findById(chatId);
        if(opt.isPresent()){
            Chat chat = opt.get();
            chatRepository.deleteById(chat.getId());
        }
    }
}
