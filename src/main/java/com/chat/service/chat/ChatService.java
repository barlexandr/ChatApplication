package com.chat.service.chat;

import com.chat.model.Chat;

import java.util.List;

public interface ChatService {

    Chat createChat(Chat chat);
    List<Chat> getAllChats();
    Chat getChatById(Integer idChat);
}
