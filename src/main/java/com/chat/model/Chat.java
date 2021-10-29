package com.chat.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "chats")
public class Chat {

    @ApiModelProperty(
            value = "Chat id. Not specified at creation",
            name = "chatId",
            dataType = "Integer",
            example = "1")
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer chatId;

    @ApiModelProperty(
            value = "Chat name",
            name = "name",
            dataType = "String",
            example = "New chat")
    @Column(nullable = false, length = 100)
    private String name;
}
