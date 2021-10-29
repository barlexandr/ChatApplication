package com.chat.service.connection;

import com.chat.model.Chat;
import com.chat.model.Connection;
import com.chat.model.User;
import com.chat.repository.ConnectionRepository;
import com.chat.service.chat.ChatService;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final UserService userService;
    private final ChatService chatService;

    @Autowired
    public ConnectionServiceImpl(ConnectionRepository connectionRepository, UserService userService, ChatService chatService) {
        this.connectionRepository = connectionRepository;
        this.userService = userService;
        this.chatService = chatService;
    }

    @Override
    public Connection enterChat(Map<String, Integer> connectionMap) {
        User user = userService.getUserById(connectionMap.get("userId"));
        Chat chat = chatService.getChatById(connectionMap.get("chatId"));

        if (user == null || chat == null || findByChatChatIdAndUserUserId(chat.getChatId(), user.getUserId()) != null) {
            return null;
        } else {
            Connection connection = new Connection();
            connection.setChat(chat);
            connection.setUser(user);
            return connectionRepository.save(connection);
        }
    }

    @Override
    public boolean leaveChat(Map<String, Integer> connectionMap) {
        User user = userService.getUserById(connectionMap.get("userId"));
        Chat chat = chatService.getChatById(connectionMap.get("chatId"));

        if (user == null || chat == null) {
            return false;
        } else {
            Connection connection = findByChatChatIdAndUserUserId(user.getUserId(), chat.getChatId());
            if (connection == null) {
                return false;
            }
            connectionRepository.delete(connection);
            return true;
        }
    }

    @Override
    public Connection findByChatChatIdAndUserUserId(Integer chatId, Integer userId) {
        return connectionRepository.findByChatChatIdAndUserUserId(chatId, userId);
    }

    @Override
    public Connection findByChatId(int chatId) {
        return connectionRepository.findConnectionByChat_ChatId(chatId);
    }
}
