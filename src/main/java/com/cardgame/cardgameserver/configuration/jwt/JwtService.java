package com.cardgame.cardgameserver.configuration.jwt;

import com.cardgame.cardgameserver.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Value("${REFRESH_SECRET}")
    private String refreshSecret;

    public String generateJwt(User user) {
        final long duration = 1000L * 60 * 15;
        return generate(user, this.jwtSecret, duration);
    }
    public String generateRefreshToken(User user) {
        final long duration = 1000L * 60 * 60 * 24 * 30;
        return generate(user, this.refreshSecret, duration);
    }

    private String generate(User user, String secret, Long durationMillis) {
        final Date currentTime = new Date(System.currentTimeMillis());
        return Jwts.builder()
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("role", user.getUserRole().name())
                .signWith(SignatureAlgorithm.HS512, secret)
                .setIssuedAt(currentTime)
                .setExpiration(new Date(currentTime.getTime() + durationMillis))
                .compact();

    }
}