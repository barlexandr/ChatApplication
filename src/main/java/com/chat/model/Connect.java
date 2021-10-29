package com.chat.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "connects")
public class Connect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int connectId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chatId")
    private Chat chat;
}
