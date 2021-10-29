package com.chat.controller;

import com.chat.model.Error;
import com.chat.model.Message;
import com.chat.service.message.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "Messages")
@RestController
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ApiOperation(
            value = "Creating message by user id, chat id and text",
            notes = "Description of the features of using this method"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return new message"),
            @ApiResponse(code = 404, message = "Bad message. User or chat or text not found"),
            @ApiResponse(code = 500, message = "Unknown server error")
    })
    @PostMapping("/message")
    public ResponseEntity<Object> create(@RequestBody Map<String, String> map) {
        Message createdMessage = messageService.create(map);
        if (createdMessage == null) {
            return new ResponseEntity<>(Error.BAD_MESSAGE, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(createdMessage, HttpStatus.OK);
        }
    }

    @ApiOperation(
            value = "Get all messages",
            notes = "Description of the features of using this method"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all messages")
    })
    @GetMapping("/messages")
    public ResponseEntity<Object> getMessages(@RequestParam(required = false) Integer chatId) {
        return new ResponseEntity<>(messageService.getAllByChatId(chatId), HttpStatus.OK);
    }
}
