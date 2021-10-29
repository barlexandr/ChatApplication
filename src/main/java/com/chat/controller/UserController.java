package com.chat.controller;

import com.chat.model.User;
import com.chat.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.chat.model.Error.*;

@Api(tags = "User")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(
            value = "Creating new user",
            notes = "Description of the features of using this method"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return new user"),
            @ApiResponse(code = 404, message = "Bad user. Name not found"),
            @ApiResponse(code = 500, message = "Unknown server error")
    })
    @PostMapping("user")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        User addedUser = userService.addUser(user);
        if (addedUser == null) {
            return new ResponseEntity<>(BAD_USER, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(addedUser, HttpStatus.OK);
        }
    }

    @ApiOperation(
            value = "Get all users",
            notes = "Description of the features of using this method"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all users")
    })
    @GetMapping("/users")
    public ResponseEntity<Object> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get user by id",
            notes = "Description of the features of using this method"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return user"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Unknown server error")
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(
            value = "Update user by id and new user info",
            notes = "Description of the features of using this method"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return new user"),
            @ApiResponse(code = 404, message = "Bad update. User not found or bad new user info"),
            @ApiResponse(code = 500, message = "Unknown server error")
    })
    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody User userForUpdate) {
        User user = userService.updateUser(id, userForUpdate);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(BAD_UPDATE, HttpStatus.BAD_REQUEST);
        }
    }
}
