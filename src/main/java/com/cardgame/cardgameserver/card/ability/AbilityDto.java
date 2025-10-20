package com.cardgame.cardgameserver.card.ability;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbilityDto {
    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    private String targetLine;
    private List<Long> targetCardTypesIds;
    private List<Integer> targetCardIds;
    @Min(1)
    @Max(10)
    private Integer numberOfTargets;
    private Boolean affectsOwnCards;
    private String changeType;
    @Min(1)
    @Max(10)
    private Integer changeValue;
    @Min(1)
    @Max(10)
    private Integer changeToValue;
    private Integer activeForAttacks;
    private Integer activeForTurns;
    private List<Long> affectingArtifactIds;
    private List<Long> affectingLocationIds;
    private String requiredLine;
    private List<Long> requiredCardIdsOnBoard;
    @Min(1)
    @Max(10)
    private Integer requiredCountOnBoard;
    @Min(1)
    @Max(100)
    private Integer requiredHealthToActivate;
    private Boolean waitsBeforeAttacking;
    @Min(1)
    @Max(100)
    private Integer attacksPerTurn;
    private Boolean capitalDrawsTwoCards;
    private Boolean canAttackHeroInLine2;
}
