package com.chat.controller;

import com.chat.model.Chat;
import com.chat.model.Error;
import com.chat.service.chat.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Chat")
@RestController
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) { this.chatService = chatService; }

    @ApiOperation(
            value = "Creating chat by chat name",
            notes = "Description of the features of using this method"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return new chat"),
            @ApiResponse(code = 404, message = "Bad chat. Name not found"),
            @ApiResponse(code = 500, message = "Unknown server error")
    })
    @PostMapping("/chat")
    public ResponseEntity<Object> createChat (@RequestBody Chat chat) {
        Chat createdChat = chatService.createChat(chat);
        if (createdChat == null) {
            return new ResponseEntity<>(Error.BAD_CHAT, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(createdChat, HttpStatus.OK);
        }
    }

    @ApiOperation(
            value = "Get all chats",
            notes = "Description of the features of using this method"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all chats")
    })
    @GetMapping("/chats")
    public ResponseEntity<Object> getAllChats() {
        return new ResponseEntity<>(chatService.getAllChats(), HttpStatus.OK);
    }
}
