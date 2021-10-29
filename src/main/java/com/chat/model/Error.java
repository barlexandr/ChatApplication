package com.chat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Error {

    USER_NOT_FOUND,
    BAD_MESSAGE,
    BAD_USER,
    BAD_UPDATE,
    BAD_CHAT,
    BAD_ENTER,
    BAD_LEAVE;

    @JsonSerialize
    String getCode() {
        return name();
    }
}

