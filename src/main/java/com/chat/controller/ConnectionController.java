package com.chat.controller;

import com.chat.model.Error;
import com.chat.model.Connection;
import com.chat.service.connection.ConnectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "User chat connection")
@RestController
public class ConnectionController {
    private final ConnectionService connectionService;

    @Autowired
    public ConnectionController(ConnectionService connectionService) { this.connectionService = connectionService; }

    @ApiOperation(
            value = "Enter to chat by user id and chat id",
            notes = "Description of the features of using this method"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return new connection"),
            @ApiResponse(code = 404, message = "Bad enter. Chat or user not found"),
            @ApiResponse(code = 500, message = "Unknown server error")
    })
    @PostMapping("/chat/enter")
    public ResponseEntity<Object> enterChat(@RequestBody Map<String, Integer> connectionMap) {
        Connection createdConnection = connectionService.enterChat(connectionMap);
        if (createdConnection == null) {
            return new ResponseEntity<>(Error.BAD_ENTER, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(createdConnection, HttpStatus.OK);
        }
    }

    @ApiOperation(
            value = "Leave chat by user id and chat id",
            notes = "Description of the features of using this method"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Bad leave. Chat or user not found"),
            @ApiResponse(code = 500, message = "Unknown server error")
    })
    @PostMapping("/chat/leave")
    public ResponseEntity<Object> leaveChat(@RequestBody Map<String, Integer> connectionMap) {
        boolean leftConnection = connectionService.leaveChat(connectionMap);

        if (leftConnection)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(Error.BAD_LEAVE, HttpStatus.BAD_REQUEST);
    }
}
