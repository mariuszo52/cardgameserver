package com.cardgame.cardgameserver.card.ability;

import com.cardgame.cardgameserver.card.CardType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Ability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    private Integer targetLine;
    private List<CardType> targetCardTypes;
    private List<Integer> targetCardIds;
    private Integer numberOfTargets;
    private Boolean affectsOwnCards;

    private AbilityChangeType changeType;
    private Integer changeValue;
    private Integer changeToValue;

    private Integer activeForAttacks;
    private Integer activeForTurns;

    private List<Long> affectingArtifactIds;
    private List<Long> affectingLocationIds;

    private Integer requiredLine;
    private List<Long> requiredCardIdsOnBoard;
    private Integer requiredCountOnBoard;
    private Integer requiredHealthToActivate;

    private Boolean waitsBeforeAttacking;
    private Integer attacksPerTurn;
    private Boolean capitalDrawsTwoCards;

    private Boolean canAttackHeroInLine2;

}
