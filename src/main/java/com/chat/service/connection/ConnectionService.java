package com.chat.service.connection;

import com.chat.model.Connection;

import java.util.Map;

public interface ConnectionService {
    Connection enterChat (Map<String, Integer> connectionMap);
    boolean leaveChat (Map<String, Integer> connectionMap);
    Connection findByChatChatIdAndUserUserId(Integer chatId, Integer userId);
    Connection findByChatId(int chatId);
}
