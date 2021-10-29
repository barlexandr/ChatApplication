package com.chat.service.connect;

import com.chat.model.Chat;
import com.chat.model.Connect;
import com.chat.model.User;
import com.chat.repository.ConnectRepository;
import com.chat.service.chat.ChatService;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConnectServiceImpl implements ConnectService {

    private final ConnectRepository connectRepository;
    private final UserService userService;
    private final ChatService chatService;

    @Autowired
    public ConnectServiceImpl(ConnectRepository connectRepository, UserService userService, ChatService chatService) {
        this.connectRepository = connectRepository;
        this.userService = userService;
        this.chatService = chatService;
    }

    @Override
    public Connect addConnect(Map<String, Integer> connectMap) {
        User user = userService.getUserById(connectMap.get("userId"));
        Chat chat = chatService.getChatById(connectMap.get("chatId"));
        if (user == null || chat == null) {
            return null;
        } else {
            Connect connect = new Connect();
            connect.setUser(user);
            connect.setChat(chat);
            return connectRepository.save(connect);
        }
    }

    @Override
    public boolean deleteConnect(Map<String, Integer> connectMap) {
        User user = userService.getUserById(connectMap.get("userId"));
        Chat chat = chatService.getChatById(connectMap.get("chatId"));
        if (user == null || chat == null) {
            return false;
        } else {
            Connect connect = new Connect();
            connect.setUser(user);
            connect.setChat(chat);
            connectRepository.delete(connect);
            return true;
        }
    }
}
