package com.cardgame.cardgameserver.configuration.jwt;

import com.cardgame.cardgameserver.googleAuth.GoogleAuthService;
import com.cardgame.cardgameserver.user.User;
import com.cardgame.cardgameserver.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    public ResponseEntity<?> oauth2callback(@RequestParam String code) {
        String idToken = googleAuthService.verifyGoogleCode(code);
        if (idToken != null) {
            String googleUserEmail = googleAuthService.getGoogleUserEmail(idToken);
            Optional<User> userOptional = userService.getUserByEmail(googleUserEmail);
            User user;
            user = userOptional.orElseGet(() -> userService.registerUser(googleUserEmail));
            TokensDto tokensDto = jwtService.generateTokens(user);
            return ResponseEntity.ok(tokensDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Google code verification failed.");
        }
    }

    @GetMapping("/refresh-token")
    ResponseEntity<String> refreshToken(@RequestParam String refreshToken) {
        try {
            String newJwt = googleAuthService.refreshJwt(refreshToken);
            return ResponseEntity.ok(newJwt);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
