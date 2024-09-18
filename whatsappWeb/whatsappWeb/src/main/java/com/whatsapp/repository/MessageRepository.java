package com.whatsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.whatsapp.modal.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("SELECT m FROM Message m JOIN m.chat c WHERE c.id=:chatId")
    public List<Message> findByChatId(@Param("chatId") Integer chatId);

}
