package com.whatsapp.repository;

import com.whatsapp.modal.Chat;
import com.whatsapp.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("SELECT c FROM Chat c JOIN c.users u WHERE u.id = :userId")
    public List<Chat> findChatByUserId(@Param("userId") Integer userId);

    @Query("SELECT c FROM Chat c WHERE c.isGroup=false AND :user MEMBER OF c.users AND :reqUser MEMBER OF c.users")
    public Chat findSingleChatByUserIds(@Param("user") User user, @Param("reqUser") User reqUser);
}
