package com.chat.service.user;

import com.chat.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> getUsers();
    User getUserById(int id);
    User updateUser(int id, User user);
}
