package com.chat.service.chat;

import com.chat.model.Chat;
import com.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements  ChatService{
    private final ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Chat createChat(Chat chat) {
        if (chat.getName() == null || chat.getChatId() == null) {
            return null;
        } else {
            return chatRepository.save(chat);
        }
    }

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    @Override
    public Chat getChatById(Integer idChat) {
        if (chatRepository.findById(idChat).isPresent()){
            return chatRepository.findById(idChat).orElse(null);
        } else {
            return null;
        }
    }
}
