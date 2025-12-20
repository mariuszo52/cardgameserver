package com.cardgame.cardgameserver.configuration.jwt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokensDto {
    @NotNull
    @NotBlank
    private String jwt;
    @NotBlank
    private String refreshToken;
}
