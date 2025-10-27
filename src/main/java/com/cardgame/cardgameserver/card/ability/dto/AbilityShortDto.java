package com.cardgame.cardgameserver.card.ability.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AbilityShortDto {
    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
}
