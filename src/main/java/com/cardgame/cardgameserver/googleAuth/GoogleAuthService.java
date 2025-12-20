package com.cardgame.cardgameserver.googleAuth;

import com.cardgame.cardgameserver.configuration.jwt.JwtService;
import com.cardgame.cardgameserver.configuration.jwt.TokensDto;
import com.cardgame.cardgameserver.user.User;
import com.cardgame.cardgameserver.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.Map;

@Service
public class GoogleAuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    @Value("${GOOGLE_CLIENT_ID}")
    private String GOOGLE_CLIENT_ID;
    @Value("${GOOGLE_CLIENT_SECRET}")
    private String GOOGLE_CLIENT_SECRET;
    @Value("${REDIRECT_URI}")
    private String REDIRECT_URI;
    @Value("${REFRESH_SECRET}")
    private String REFRESH_SECRET;

    private final RestTemplate restTemplate = new RestTemplate();

    public GoogleAuthService(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public String getGoogleUserEmail(String idToken) {
        String tokenInfoUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
        ResponseEntity<Map> tokenInfoResp = restTemplate.getForEntity(tokenInfoUrl, Map.class);
        Map<String, Object> tokenInfo = tokenInfoResp.getBody();
        return tokenInfo.get("email").toString();

    }

    public String verifyGoogleCode(String code) {
        String url = "https://oauth2.googleapis.com/token";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", code);
        body.add("client_id", GOOGLE_CLIENT_ID);
        body.add("client_secret", GOOGLE_CLIENT_SECRET);
        body.add("redirect_uri", REDIRECT_URI);
        body.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    Map.class
            );

            Map<String, Object> tokenResponse = response.getBody();
            if (tokenResponse != null) {
                return tokenResponse.get("id_token").toString();
            } else return null;
        } catch (Exception e) {
            return null;
        }
    }


    public TokensDto refreshToken(@NotNull String refreshToken) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(REFRESH_SECRET).build().parseClaimsJws(refreshToken);
        Claims claims = claimsJws.getBody();
        String email = claims.get("email", String.class);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        Date refreshExpiration = claims.getExpiration();
        if (refreshExpiration.before(new Date(Instant.now().plus(2, ChronoUnit.DAYS).toEpochMilli()))) {
            return jwtService.generateTokens(user);
        }else {
            String jwt = jwtService.generateJwt(user);
            return new TokensDto(jwt, null);
        }

    }

}
