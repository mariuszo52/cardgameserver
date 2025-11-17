package com.cardgame.cardgameserver.card.ability.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AbilityDto {
    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    private String changeType;
    @Min(1)
    @Max(10)
    private Integer changeValue;
    @Min(1)
    @Max(10)
    private Integer changeToValue;
    private Integer activeForTurns;
    @NotNull
    private Boolean isMeditation;
}
