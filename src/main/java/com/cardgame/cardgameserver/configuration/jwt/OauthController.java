package com.cardgame.cardgameserver.configuration.jwt;

import com.cardgame.cardgameserver.googleAuth.GoogleAuthService;
import com.cardgame.cardgameserver.user.User;
import com.cardgame.cardgameserver.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {

    private final GoogleAuthService googleAuthService;
    private final UserService userService;
    private final JwtService jwtService;

    public OauthController(GoogleAuthService googleAuthService, UserService userService, JwtService jwtService) {
        this.googleAuthService = googleAuthService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/oauth2callback")
    public String oauth2callback(@RequestParam String code) {
        String idToken = googleAuthService.verifyGoogleCode(code);
        if (idToken != null) {
            String googleUserEmail = googleAuthService.getGoogleUserEmail(idToken);
            User user = userService.registerUser(googleUserEmail);
            return jwtService.generateJwt(user);
        }else {
            throw new RuntimeException("Google code verification failed.");
        }
    }
}
