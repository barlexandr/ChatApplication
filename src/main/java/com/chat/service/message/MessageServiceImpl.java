package com.chat.service.message;

import com.chat.model.Message;
import com.chat.repository.MessageRepository;
import com.chat.service.chat.ChatService;
import com.chat.service.connection.ConnectionService;
import com.chat.service.user.UserService;
import com.chat.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    private static final String SEND_TIME_PROPERTY = "sendTime";

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ConnectionService connectionService;
    private final ChatService chatService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,
                              UserServiceImpl userService,
                              ConnectionService connectionService, ChatService chatService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.connectionService = connectionService;
        this.chatService = chatService;
    }

    public Message create(Map<String, String> map) {
        map.putIfAbsent("lifetimeSec", "-1");
        map.putIfAbsent("chatId", "0");
        int chatId = Integer.parseInt(map.get("chatId"));
        int lifetimeSec = Integer.parseInt(map.get("lifetimeSec"));
        Message message = new Message();
        message.setUserId(Integer.parseInt(map.get("userId")));
        message.setText(map.get("text"));
        message.setLifetimeSec(lifetimeSec);
        message.setSendTime(java.time.LocalDateTime.now());

        if (userService.getUserById(message.getUserId()) == null || message.getText() == null) {
            return null;
        } else if (connectionService.findByChatId(chatId) != null) {
            if (connectionService.findByChatChatIdAndUserUserId(chatId, message.getUserId()) != null)
                message.setChat(chatService.getChatById(chatId));
            else
                return null;
        } else if (chatId != 0)
            return null;
        return messageRepository.save(message);
    }

    public List<Message> getAllByChatId(Integer chatId) {
        List<Message> messageListFromDb = null;

        if (chatId == null) {
            messageListFromDb = messageRepository.findAll(Sort.by(Sort.Direction.DESC, SEND_TIME_PROPERTY));
        } else if (chatService.getChatById(chatId) != null){
            messageListFromDb = messageRepository.findMessagesByChat_ChatId(chatId);
            messageListFromDb.sort((Comparator.comparing(Message::getSendTime).reversed()));
        }
        return messageListFromDb;
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public void deleteMessages(List<Message> messages) {
        messageRepository.deleteAll(messages);
    }
}
