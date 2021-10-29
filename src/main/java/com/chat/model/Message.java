package com.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity(name = "messages")
public class Message {
    @ApiModelProperty(
            value = "Message id. Not specified at creation",
            name = "messageId",
            dataType = "Integer",
            example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    @ApiModelProperty(
            value = "User id",
            name = "userId",
            dataType = "Integer",
            example = "1")
    @Column(nullable = false)
    private Integer userId;

    @ApiModelProperty(
            value = "Chat class",
            name = "chat",
            dataType = "Chat")
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "chatId")
    private Chat chat;

    @ApiModelProperty(
            value = "Message text",
            name = "text",
            dataType = "String",
            example = "Hello world!")
    @Column(nullable = false, length = 3000)
    private String text;

    @ApiModelProperty(
            value = "Message send time",
            name = "sendTime",
            dataType = "LocalDateTime")
    @Column(nullable = false)
    private LocalDateTime sendTime;

    @ApiModelProperty(
            value = "Message life time",
            name = "lifetimeSec",
            dataType = "Integer")
    @Column(nullable = false)
    private Integer lifetimeSec;
}
