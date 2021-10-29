package com.chat.service.user;

import com.chat.model.User;
import com.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        if (user.getFirstName() == null || user.getLastName() == null) {
            return null;
        } else {
            return userRepository.save(user);
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(int id, User user) {
        if (userRepository.existsById(id)) {
            if (user.getFirstName() == null || user.getLastName() == null) {
                return null;
            } else {
                user.setUserId(id);
                return userRepository.save(user);
            }
        }
        return null;
    }
}
