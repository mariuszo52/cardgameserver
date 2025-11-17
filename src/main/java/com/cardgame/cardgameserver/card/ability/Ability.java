package com.cardgame.cardgameserver.card.ability;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Ability
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    @Enumerated(EnumType.STRING)
    private AbilityChangeType changeType;
    @Min(1)
    @Max(10)
    private Integer changeValue;
    @Min(0)
    @Max(100)
    private Integer changeToValue;
    private Integer activeForTurns;
    @NotNull
    private Boolean isMeditation;

}
