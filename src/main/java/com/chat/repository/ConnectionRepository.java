package com.chat.repository;

import com.chat.model.Chat;
import com.chat.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Integer> {
    Connection findByChatChatIdAndUserUserId(Integer chatId, Integer userId);
    Connection findConnectionByChat_ChatId(Integer chatId);
}
