package com.cardgame.cardgameserver.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User registerUser(String email){
        return User.builder()
                .email(email)
                .password(email)
                .isDeleted(false)
                .userRole(UserRole.USER)
                .isEnabled(true)
                .build();
    }
}
