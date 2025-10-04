package com.cardgame.cardgameserver.configuration.jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {


    @GetMapping("/oauth2callback")
    public String oauth2callback(@RequestParam String code) {
        System.out.println(code);
        return "";
    }
}
