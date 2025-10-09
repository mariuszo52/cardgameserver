package com.cardgame.cardgameserver.googleAuth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GoogleAuthService {

    @Value("${GOOGLE_CLIENT_ID}")
    private String GOOGLE_CLIENT_ID;
    @Value("${GOOGLE_CLIENT_SECRET}")
    private String GOOGLE_CLIENT_SECRET;
    @Value("${REDIRECT_URI}")
    private String REDIRECT_URI;

    private final RestTemplate restTemplate = new RestTemplate();

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
}
