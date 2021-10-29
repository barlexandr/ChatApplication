package com.chat.service.message;

import com.chat.model.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {
    Message create(Map<String, String> map);
    List<Message> getAllByChatId(Integer chatId);
    List<Message> getAll();
    void deleteMessages(List<Message> messages);
}
