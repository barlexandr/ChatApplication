package com.chat.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "connections")
public class Connection {
    @ApiModelProperty(
            value = "Connection id. Not specified at creation",
            name = "connectionId",
            dataType = "Integer",
            example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer connectionId;

    @ApiModelProperty(
            value = "Chat class",
            name = "chat",
            dataType = "Chat")
    @ManyToOne
    @JoinColumn(name = "chatId")
    private Chat chat;

    @ApiModelProperty(
            value = "User class",
            name = "user",
            dataType = "User")
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
