package com.chat.configuration;

import com.chat.model.Message;
import com.chat.service.message.MessageService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class LifetimeExpirationConfig {
    private final MessageService messageService;

    public LifetimeExpirationConfig(MessageService messageService) {
        this.messageService = messageService;
    }

    @Scheduled(fixedDelay = 1000)
    private void scheduledDatabaseUpdate() {
        List<Message> messagesForDelete = messageService
                .getAll()
                .stream()
                .filter(message -> message.getSendTime()
                        .plusSeconds(message.getLifetimeSec())
                        .isBefore(LocalDateTime.now()) && message.getLifetimeSec() != -1)
                .collect(Collectors.toList());
        messageService.deleteMessages(messagesForDelete);
    }
}
