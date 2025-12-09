package com.cardgame.cardgameserver.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String email) {
        User user = User.builder()
                .email(email)
                .password(email)
                .isDeleted(false)
                .userRole(UserRole.USER)
                .isEnabled(true)
                .build();
        return userRepository.save(user);
    }


    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
